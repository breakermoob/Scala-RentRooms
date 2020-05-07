package models

// Importes necesarios para hacer el modelo Room
import com.sun.org.apache.xpath.internal.operations.Or
import play.api.libs.json.{JsPath, Json, Reads}
import play.api.libs.functional.syntax._

// Se crea la clase Room, en la cual los parámetros deben coincidir con los campos de la tabla Rooms de la base de datos
case class Room(id: Int, name: String)

// También se crea un objeto Room con el fin de implementar los métodos para escribir Room como si fueran Jsons
object Room {
  implicit val roomWrite = Json.writes[Room]
  implicit val roomRead = Json.reads[Room]
}
