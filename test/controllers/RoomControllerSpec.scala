package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.Future
import play.api.mvc._
import play.api.Application
import controllers._

class RoomControllerSpec extends PlaySpec with Results {
  
  val controller = new RoomController(stubControllerComponents())

//   "Convirtiendo el 1 a romano" should {
//     "should be valid" in {
//       val result = controller.decToRoman(1)
//       result mustBe "I"
//     }
//   }
  

}
