// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Thu May 07 15:23:16 COT 2020


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
