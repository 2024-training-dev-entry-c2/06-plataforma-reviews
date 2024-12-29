package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;

public class CreateRestaurant implements ICommand {
  private RestaurantService service;
  private final IHandler handler;

  public CreateRestaurant(RestaurantService service, IHandler handler){
    this.service = service;
    this.handler = handler;
  }


  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante: ");
    String name = handler.readLine();
    handler.writeLine("Indica la dirección del restaurante: ");
    String address = handler.readLine();
    handler.writeLine("¿El restaurante está disponible?(Si/No)");
    Boolean available = handler.readLine().equalsIgnoreCase("Si");
    if(service.createRestaurant(new Restaurant(name, address, available))){
      System.out.println("¡El restaurante ha sido creado con éxito!");
    }else{
      System.out.println("No se ha podido crear el restaurante.");
    }
  }
}
