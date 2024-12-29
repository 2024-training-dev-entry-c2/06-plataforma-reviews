package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.services.RestaurantService;

public class DeleteRestaurant implements ICommand {
  private RestaurantService service;
  private final IHandler handler;

  public DeleteRestaurant(RestaurantService service, IHandler handler){
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante: ");
    String name = handler.readLine();
    if(service.deleteRestaurant(service.getRestaurantByName(name))){
      System.out.println("Se ha eliminado el restaurante " + name + ".");
    }else{
      System.out.println("No se ha podido eliminar el restaurante.");
    }
  }
}
