package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Room
import play.api.db._ 

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database,cc: ControllerComponents) extends AbstractController(cc) {

  val jsonAux = Json.toJson(rooms) // Finalmente, se Jsifican los resultados
  Ok(jsonAux) // Y se retorna la lista de mascotas Jsificada
}

  def getRooms = Action {
  // En primer lugar creamos una variable para realizar la conexion con la BD
  val conexion = db.getConnection()
  
  // A continuación inicializamos (vaciamos) la lista con la que procesaremos los datos que lleguen de la BD
  var rooms = List[Room]()
    
  try{
  // Ahora creamos una variable en donde formulamos nuestra query SQL de búsqueda y la ejecutamos
    val query = conexion.createStatement
    val resultado = query.executeQuery("SELECT * FROM rooms")
      
    // Ya con el resultado de la consulta, creamos objetos mascota y los agregamos a la lista de apoyo
    while (resultado.next()) {
      var p = Room(resultado.getInt("id"), resultado.getString("name"))
      rooms = rooms :+ p
    }
  }
  finally{
    // Antes de retornar los resultados, cerramos la conexión a la BD
    conexion.close()
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
