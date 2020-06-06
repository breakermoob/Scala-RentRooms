package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

 
class RoomControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
    "/rooms/search route" must {
      "search rooms successfully" in {
        val controller = inject[RoomController]
        val service = controller.search("BOG","2028-06-04","2028-06-09").apply(FakeRequest(GET, "/rooms/search"))
      
        status(service) mustBe OK
        contentType(service) mustBe Some("application/json")
        contentAsString(service) must include ("""{"id":"2",""")
      }

      "search rooms with dates in the past" in {
        val controller = inject[RoomController]
        val service = controller.search("BOG","2018-06-04","2018-06-09").apply(FakeRequest(GET, "/rooms/search"))
      
        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("Checkin date could not be in the past.")
      }

      "search rooms with checkout before checkin" in {
        val controller = inject[RoomController]
        val service = controller.search("BOG","2018-06-09","2018-06-04").apply(FakeRequest(GET, "/rooms/search"))
      
        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("Checkin date should be before checkout.")
      }
    }

    
    "/rooms/:id route" must {
      "get room details successfully" in {
        val controller = inject[RoomController]
        val service = controller.detail(2).apply(FakeRequest(GET, "/rooms/2"))
      
        status(service) mustBe OK
        contentType(service) mustBe Some("application/json")
        contentAsString(service) must include ("""{"id":"2",""")
      }

      "get nothing for non existent room" in {
        val controller = inject[RoomController]
        val service = controller.detail(10).apply(FakeRequest(GET, "/rooms/10"))
      
        status(service) mustBe OK
        contentType(service) mustBe Some("application/json")
        contentAsString(service) must include ("""{}""")
      }
    }

    "/booking route" must {
      "try to book an occupied room" in {
        val controller = inject[RoomController]
        val payload = """{
          "checkin": "2025-06-08",
          "checkout": "2025-06-09",
          "email": "leon.arango@udea.edu.co",
          "name": "Leon Arango",
          "id_room": 1
        }"""
        val service = controller.booking().apply(FakeRequest(POST, "/booking").withBody(Json.parse(payload)).withHeaders("authtoken" -> "test_token"))

        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("Occupied room.")
      }
      
      "get error for user not authenticated" in {
        val controller = inject[RoomController]
        val payload = """{
          "checkin": "2025-06-08",
          "checkout": "2025-06-09",
          "email": "leon.arango@udea.edu.co",
          "name": "Leon Arango",
          "id_room": 1
        }"""
        val service = controller.booking().apply(FakeRequest(POST, "/booking").withBody(Json.parse(payload)).withHeaders("authtoken" -> "wrong_token"))

        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("User not authenticated.")
      }

      // "Prueba de servicio ~ Buscar inmuebles" in {
      //   val controller = inject[RoomController]
      //   val payload = """{
      //     "checkin": "2025-06-08",
      //     "checkout": "2025-06-09",
      //     "email": "leon.arango@udea.edu.co",
      //     "name": "Leon Arango",
      //     "id_room": 1
      //   }"""
      //   val service = controller.booking().apply(FakeRequest(POST, "/booking").withBody(Json.parse(payload)))

      //   status(service) mustBe OK
      //   contentType(service) mustBe Some("application/json")
      //   contentAsString(service) must include (
      //     """{
      //           "id_booking": "25",
      //           "checkin": "2025-06-08",
      //           "checkout": "2025-06-09",
      //           "email": "leon.arango@udea.edu.co",
      //           "name": "Leon Arango",
      //           "id_room": 1
      //       }""")
      //   }
    }

    "/bookings/:email route" must {
      "get all user's bookings successfully" in {
        val controller = inject[RoomController]
        val service = controller.userBookings("leon.arango@udea.edu.co").apply(FakeRequest(GET, "/bookings/").withHeaders("authtoken" -> "test_token"))
      
        status(service) mustBe OK
        contentType(service) mustBe Some("application/json")
        contentAsString(service) must include ("""[{"id_room":"1","thumbnail":"https://rentrooms.s3.amazonaws.com/MDE/MDE-1-tub.jpg","location":{"name":"Medellin","code":"MDE","latitude":6.230833,"longitude":-75.590553}""")
      }
      
      "get nothing for non existent user" in {
        val controller = inject[RoomController]
        val service = controller.userBookings("nico.henao@udea.edu.co").apply(FakeRequest(GET, "/bookings/").withHeaders("authtoken" -> "test_token"))
      
        status(service) mustBe OK
        contentType(service) mustBe Some("application/json")
        contentAsString(service) must include ("""[]""")
      }
      
      "get error for user not authenticated" in {
        val controller = inject[RoomController]
        val service = controller.userBookings("leon.arango@udea.edu.co").apply(FakeRequest(GET, "/bookings/").withHeaders("authtoken" -> "wrong_token"))
      
        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("User not authenticated.")
      }
    }
}