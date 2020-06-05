package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._


class RoomControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
    

    "Service Test ~ Function getRooms ~ Successfully" in {
      val controller = inject[RoomController]
      val service = controller.getRooms().apply(FakeRequest(GET, "/rooms"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("text/plain")
      contentAsString(service) must include ("""ok""")
    }
    "Service Test ~ Function detail ~ Successfully" in {
      val controller = inject[RoomController]
      val service = controller.detail(2).apply(FakeRequest(GET, "/rooms/2"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("application/json")
      contentAsString(service) must include ("""{"id":"2",""")
    }
    "Service Test ~ Function detail ~ Room not found" in {
      val controller = inject[RoomController]
      val service = controller.detail(10).apply(FakeRequest(GET, "/rooms/10"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("application/json")
      contentAsString(service) must include ("""{}""")
    }

      "Service Test ~ Function booking ~ Ocupped Room" in {
        val controller = inject[RoomController]
        val payload = """{
          "checkin": "2025-06-08",
          "checkout": "2025-06-09",
          "email": "leon.arango@udea.edu.co",
          "name": "Leon Arango",
          "id_room": 1
        }"""
        val service = controller.booking().apply(FakeRequest(POST, "/booking").withBody(Json.parse(payload)))

        status(service) mustBe BAD_REQUEST
        contentType(service) mustBe Some("text/plain")
        contentAsString(service) must include ("Occupied room.")
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

      "Service Test ~ Function search ~ Successfully" in {
      val controller = inject[RoomController]
      val service = controller.search("BOG","2028-06-04","2028-06-09").apply(FakeRequest(GET, "/rooms/search"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("application/json")
      contentAsString(service) must include ("""{"id":"2",""")
    }
      "Service Test ~ Function search ~ Past Date" in {
      val controller = inject[RoomController]
      val service = controller.search("BOG","2018-06-04","2018-06-09").apply(FakeRequest(GET, "/rooms/search"))
    
      status(service) mustBe BAD_REQUEST
      contentType(service) mustBe Some("text/plain")
      contentAsString(service) must include ("Checkin date could not be in the past.")
    }
      "Service Test ~ Function search ~ Before Date" in {
      val controller = inject[RoomController]
      val service = controller.search("BOG","2018-06-09","2018-06-04").apply(FakeRequest(GET, "/rooms/search"))
    
      status(service) mustBe BAD_REQUEST
      contentType(service) mustBe Some("text/plain")
      contentAsString(service) must include ("Checkin date should be before checkout.")
    }

    "Service Test ~ Function userBookings ~ Successfully" in {
      val controller = inject[RoomController]
      val service = controller.userBookings("leon.arango@udea.edu.co").apply(FakeRequest(GET, "/bookings/"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("application/json")
      contentAsString(service) must include ("""[{"id_room":"1","thumbnail":"https://rentrooms.s3.amazonaws.com/MDE/MDE-1-tub.jpg","location":{"name":"Medellin","code":"MDE","latitude":6.230833,"longitude":-75.590553}""")
    }
    "Service Test ~ Function userBookings ~ user not found" in {
      val controller = inject[RoomController]
      val service = controller.userBookings("nico.henao@udea.edu.co").apply(FakeRequest(GET, "/bookings/"))
    
      status(service) mustBe OK
      contentType(service) mustBe Some("application/json")
      contentAsString(service) must include ("""[]""")
    }


}