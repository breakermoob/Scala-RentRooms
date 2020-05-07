// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/break/Desktop/RentRooms/conf/routes
// @DATE:Wed May 06 20:32:28 COT 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
