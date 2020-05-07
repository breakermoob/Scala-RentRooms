// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/nico9/Desktop/Empresariales/git/RentRooms/conf/routes
// @DATE:Wed May 06 22:52:58 EDT 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRoomController RoomController = new controllers.ReverseRoomController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRoomController RoomController = new controllers.javascript.ReverseRoomController(RoutesPrefix.byNamePrefix());
  }

}
