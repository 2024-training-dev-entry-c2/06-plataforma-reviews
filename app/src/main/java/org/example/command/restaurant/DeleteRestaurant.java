package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.services.RestaurantService;
import org.example.utils.ConsoleHandler;

public class DeleteRestaurant implements ICommand {
  private RestaurantService service;
  private final ConsoleHandler console;

  public DeleteRestaurant(RestaurantService service, ConsoleHandler console){
    this.service = service;
    this.console = console;
  }

  @Override
  public void execute() {
    console.writeLine("Indica el nombre del restaurante: ");
    String name = console.readLine();
    if(service.deleteRestaurant(service.getRestaurantByName(name))){
      System.out.println("Se ha eliminado el restaurante " + name + ".");
    }else{
      System.out.println("No se ha podido eliminar el restaurante.");
    }
  }
}
