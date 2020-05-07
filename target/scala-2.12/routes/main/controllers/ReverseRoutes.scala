// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Wed May 06 22:52:58 EDT 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseRoomController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def booking(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "booking")
    }
  
    // @LINE:10
    def search(location:String, checkin:String, checkout:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "rooms/search" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("location", location)), Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("checkin", checkin)), Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("checkout", checkout)))))
    }
  
    // @LINE:11
    def detail(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "rooms/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:7
    def getRooms(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "rooms")
    }
  
  }


}
