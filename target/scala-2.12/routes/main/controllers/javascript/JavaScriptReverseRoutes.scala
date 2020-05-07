// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Wed May 06 22:52:58 EDT 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseRoomController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def booking: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RoomController.booking",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "booking"})
        }
      """
    )
  
    // @LINE:10
    def search: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RoomController.search",
      """
        function(location0,checkin1,checkout2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rooms/search" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("location", location0), (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("checkin", checkin1), (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("checkout", checkout2)])})
        }
      """
    )
  
    // @LINE:11
    def detail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RoomController.detail",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rooms/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:7
    def getRooms: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RoomController.getRooms",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rooms"})
        }
      """
    )
  
  }


}
