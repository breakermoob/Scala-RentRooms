package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.{Booking, Location, Room}
import play.api.db._
import java.text.SimpleDateFormat;  

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class RoomController @Inject()(db: Database,cc: ControllerComponents) extends AbstractController(cc) {

  def getRooms = Action {
    /*
    // En primer lugar creamos una variable para realizar la conexion con la BD
    val conexion = db.getConnection()
    
    // A continuación inicializamos (vaciamos) la lista con la que procesaremos los datos que lleguen de la BD
    //var roomsRes = List[JsValue]()
      
    try{
    // Ahora creamos una variable en donde formulamos nuestra query SQL de búsqueda y la ejecutamos
      val query = conexion.createStatement
      val rooms = query.executeQuery("SELECT * FROM rooms r INNER JOIN locations l ON r.locationId = l.id")
        
      // Ya con el resultado de la consulta, creamos objetos mascota y los agregamos a la lista de apoyo
      val roomsRes: Iterator[JsValue] = Iterator.continually(rooms).takeWhile(_.next()).map{ rooms =>
        val roomId = rooms.getInt("r.id")
        val bookings = query.executeQuery(s"SELECT * FROM bookings WHERE roomId = $roomId")
        
        val bookingsRes: Iterator[Boolean] = Iterator.continually(bookings).takeWhile(_.next()).some{ bookings =>
          val bCheckin = rooms.getDate("checkin")
          val bCheckout = rooms.getDate("checkout")
          (checkin =< bCheckin && checkout >= bCheckout) || (checkin > bCheckin && checkin < bCheckout) || (checkout > bCheckin && checkout < bCheckout)
        }

        if (!bookingsRes) {
          val json: JsValue = Json.obj(
            "id" -> roomId,
            "thumbnail" -> rooms.getString("r.thumbnail"),
            "location" -> Json.obj(
              "name" -> rooms.getString("l.name"),
              "code" -> rooms.getString("l.code"),
              "latitude" -> rooms.getDouble("l.latitude"),
              "longitude" -> rooms.getDouble("l.longitude")
            ),
            "price" -> rooms.getDouble("r.price"),
            "currency" -> "COP",
            "agency" -> Json.obj(
              "name" -> "Una Agencia",
              "id" -> 1234
            ),
            "property_name" -> rooms.getString("r.name"),
            "rating" -> rooms.getDouble("r.rating")
          )

          json
        }
      }
    }
    finally{
      // Antes de retornar los resultados, cerramos la conexión a la BD
      conexion.close()
    }
    val jsonAux = Json.toJson(roomsRes) // Finalmente, se Jsifican los resultados
    Ok(jsonAux) // Y se retorna la lista de habitaciones Jsificada
    */
    Ok("ok")
  }

  //Service to search rooms available
  def search(location: String, checkin: String, checkout: String) = Action { implicit request: Request[AnyContent] =>
    // En primer lugar creamos una variable para realizar la conexion con la BD
    val conexion = db.getConnection()
    
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    val qCheckin = dateFormat.parse(checkin)
    val qCheckout = dateFormat.parse(checkout)

    try{
    // Ahora creamos una variable en donde formulamos nuestra query SQL de búsqueda y la ejecutamos
      val query = conexion.createStatement
      val rooms = query.executeQuery(s"SELECT * FROM rooms r INNER JOIN locations l ON r.locationId = l.id WHERE l.code = '$location'")
        
      // Ya con el resultado de la consulta, creamos objetos mascota y los agregamos a la lista de apoyo
      val roomsRes: List[JsValue] = Iterator.continually(rooms).takeWhile(_.next()).map{ rooms =>
        val roomId = rooms.getInt("r.id")
        val bookings = query.executeQuery(s"SELECT * FROM bookings WHERE roomId = $roomId")
        
        val bookingsRes: List[Boolean] = Iterator.continually(bookings).takeWhile(_.next()).map{ bookings =>
          val bCheckin = rooms.getDate("checkin")
          val bCheckout = rooms.getDate("checkout")
          if ((qCheckin.compareTo(bCheckin) =< 0  && qCheckout.compareTo(bCheckout) >= 0) || (qCheckin.compareTo(bCheckin) >= 0 && qCheckin.compareTo(bCheckout) < 0) || (qCheckout.compareTo(bCheckin) > 0 && qCheckout.compareTo(bCheckout) =< 0)) true
        }.toList

        if (bookingsRes == Nil) {
          val json: JsValue = Json.obj(
            "id" -> roomId,
            "thumbnail" -> rooms.getString("r.thumbnail"),
            "location" -> Json.obj(
              "name" -> rooms.getString("l.name"),
              "code" -> rooms.getString("l.code"),
              "latitude" -> rooms.getDouble("l.latitude"),
              "longitude" -> rooms.getDouble("l.longitude")
            ),
            "price" -> rooms.getDouble("r.price"),
            "currency" -> "COP",
            "agency" -> Json.obj(
              "name" -> "Una Agencia",
              "id" -> 1234
            ),
            "property_name" -> rooms.getString("r.name"),
            "rating" -> rooms.getDouble("r.rating")
          )

          json
        }
      }.toList
      
      val jsonAux = Json.toJson(roomsRes) // Finalmente, se Jsifican los resultados
      Ok(jsonAux) // Y se retorna la lista de habitaciones Jsificada
    }/*
    catch{
      case e: Exception => BadRequest(e)
    }*/
    finally{
      // Antes de retornar los resultados, cerramos la conexión a la BD
      conexion.close()
    }
  }
  
  //Service to search rooms available
  def detail(id: Long) = Action { implicit request: Request[AnyContent] =>
    val json: JsValue = Json.obj(
      "id" -> id
    )
    Ok(json)
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
