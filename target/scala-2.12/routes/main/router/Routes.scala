// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Thu May 07 15:23:16 COT 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  RoomController_0: controllers.RoomController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    RoomController_0: controllers.RoomController
  ) = this(errorHandler, RoomController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, RoomController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rooms""", """controllers.RoomController.getRooms"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rooms/search""", """controllers.RoomController.search(location:String, checkin:String, checkout:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rooms/""" + "$" + """id<[^/]+>""", """controllers.RoomController.detail(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """booking""", """controllers.RoomController.booking"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_RoomController_getRooms0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rooms")))
  )
  private[this] lazy val controllers_RoomController_getRooms0_invoker = createInvoker(
    RoomController_0.getRooms,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RoomController",
      "getRooms",
      Nil,
      "GET",
      this.prefix + """rooms""",
      """ Rutas Rent&Rooms""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_RoomController_search1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rooms/search")))
  )
  private[this] lazy val controllers_RoomController_search1_invoker = createInvoker(
    RoomController_0.search(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RoomController",
      "search",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """rooms/search""",
      """ rooms""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_RoomController_detail2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rooms/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RoomController_detail2_invoker = createInvoker(
    RoomController_0.detail(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RoomController",
      "detail",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """rooms/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_RoomController_booking3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("booking")))
  )
  private[this] lazy val controllers_RoomController_booking3_invoker = createInvoker(
    RoomController_0.booking,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RoomController",
      "booking",
      Nil,
      "POST",
      this.prefix + """booking""",
      """ booking""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_RoomController_getRooms0_route(params@_) =>
      call { 
        controllers_RoomController_getRooms0_invoker.call(RoomController_0.getRooms)
      }
  
    // @LINE:10
    case controllers_RoomController_search1_route(params@_) =>
      call(params.fromQuery[String]("location", None), params.fromQuery[String]("checkin", None), params.fromQuery[String]("checkout", None)) { (location, checkin, checkout) =>
        controllers_RoomController_search1_invoker.call(RoomController_0.search(location, checkin, checkout))
      }
  
    // @LINE:11
    case controllers_RoomController_detail2_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RoomController_detail2_invoker.call(RoomController_0.detail(id))
      }
  
    // @LINE:14
    case controllers_RoomController_booking3_route(params@_) =>
      call { 
        controllers_RoomController_booking3_invoker.call(RoomController_0.booking)
      }
  }
}
