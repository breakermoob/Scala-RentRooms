// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Thu May 07 15:23:16 COT 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRoomController RoomController = new controllers.ReverseRoomController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRoomController RoomController = new controllers.javascript.ReverseRoomController(RoutesPrefix.byNamePrefix());
  }

}
