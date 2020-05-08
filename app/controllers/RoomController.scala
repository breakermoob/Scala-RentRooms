package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.{Booking, Location, Room}
import play.api.db._ 

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class RoomController @Inject()(db: Database,cc: ControllerComponents) extends AbstractController(cc) {

  def getRooms = Action {
    // En primer lugar creamos una variable para realizar la conexion con la BD
    val conexion = db.getConnection()
    
    // A continuación inicializamos (vaciamos) la lista con la que procesaremos los datos que lleguen de la BD
    var rooms = List[JsValue]()
      
    try{
    // Ahora creamos una variable en donde formulamos nuestra query SQL de búsqueda y la ejecutamos
      val query = conexion.createStatement
      val resultado = query.executeQuery("SELECT * FROM rooms r INNER JOIN locations l ON r.locationId = l.id")
        
      // Ya con el resultado de la consulta, creamos objetos mascota y los agregamos a la lista de apoyo
      while (resultado.next()) {
        val json: JsValue = Json.obj(
          "id" -> resultado.getInt("r.id"),
          "thumbnail" -> resultado.getString("r.thumbnail"),
          "location" -> Json.obj(
            "name" -> resultado.getString("l.name"),
            "code" -> resultado.getString("l.code"),
            "latitude" -> resultado.getDouble("l.latitude"),
            "longitude" -> resultado.getDouble("l.longitude")
          ),
          "price" -> resultado.getDouble("r.price"),
          "currency" -> "COP",
          "agency" -> Json.obj(
            "name" -> "Una Agencia",
            "id" -> 1234
          ),
          "property_name" -> resultado.getString("r.name"),
          "rating" -> resultado.getDouble("r.rating")
        )
        rooms = rooms :+ json
      }
    }
    finally{
      // Antes de retornar los resultados, cerramos la conexión a la BD
      conexion.close()
    }
    val jsonAux = Json.toJson(rooms) // Finalmente, se Jsifican los resultados
    Ok(jsonAux) // Y se retorna la lista de habitaciones Jsificada
  }

  //Service to search rooms available
  def search(location: String, checkin: String, checkout: String) = Action { implicit request: Request[AnyContent] =>
    val json: JsValue = Json.obj(
      "location" -> location,
      "checkin" -> checkin,
      "checkout" -> checkout
    )
    Ok(json)
  }
  
  //Service to search rooms available
  def detail(id: Long) = Action { implicit request: Request[AnyContent] =>
    // En primer lugar creamos una variable para realizar la conexion con la BD
    val conexion = db.getConnection()  
    // A continuación inicializamos (vaciamos) la lista con la que procesaremos los datos que lleguen de la BD
    var images = List[JsValue]()    
    var services = List[String]() 
    var room: JsValue = Json.obj()   
    try{
      val query = conexion.createStatement      
      
      val resultadoImages = query.executeQuery(s"SELECT url FROM room_images ri WHERE ri.roomId = $id;")      
      while(resultadoImages.next){
        val jsonImage: JsValue = Json.obj(
          "url" -> resultadoImages.getString("url")
        )
        images = images :+ jsonImage
      }      

      val resultadoServices = query.executeQuery(s"SELECT DISTINCT name FROM services_per_room spr INNER JOIN services s ON spr.serviceId = s.id WHERE spr.roomId = $id;")     
      while(resultadoServices.next){
        val jsonService: String = resultadoServices.getString("name")
        services = services :+ jsonService
      } 

      val resultadoRoom = query.executeQuery(s"SELECT DISTINCT * FROM rooms r INNER JOIN locations l ON r.locationId = l.id WHERE r.id = $id;")
      while(resultadoRoom.next){
        val jsonRoom: JsValue = Json.obj(
          "id" -> resultadoRoom.getInt("r.id"),
          "images" -> images,
          "location" -> Json.obj(
            "name" -> resultadoRoom.getString("l.name"),
            "code" -> resultadoRoom.getString("l.code"),
            "latitude" -> resultadoRoom.getDouble("l.latitude"),
            "longitude" -> resultadoRoom.getDouble("l.longitude")
          ),
          "price" -> resultadoRoom.getDouble("r.price"),
          "currency" -> "COP",
          "agency" -> Json.obj(
            "name" -> "Una Agencia",
            "id" -> 1234,
            "logo_url" -> "https://banner2.kisspng.com/20180606/yer/kisspng-play-framework-scala-software-framework-java-web-a-play-again-5b1896eec63338.1231269915283381588118.jpg"
          ),
          "property_name" -> resultadoRoom.getString("r.name"),
          "rating" -> resultadoRoom.getDouble("r.rating"),
          "services" -> services
        )
        room = jsonRoom   
      }
    }
    finally{
      // Antes de retornar los resultados, cerramos la conexión a la BD
      conexion.close()
    }
    val jsonAux = Json.toJson(room) // Finalmente, se Jsifican los resultados
    Ok(jsonAux) // Y se retorna la lista de habitaciones Jsificada    
  }
  
  //Service to search rooms available
  def booking = Action(parse.json) { implicit request =>
    request.body.validate[Booking].map{ 
      case success => Ok("json")
      case e:JsError => BadRequest("No se pudo actualizar porque hay malos parametros!!")
    }.recoverTotal{
      e => BadRequest("Detected error:"+ e + request.body)
    }
  }
}
