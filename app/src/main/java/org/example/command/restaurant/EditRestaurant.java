package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.services.RestaurantService;

public class EditRestaurant implements ICommand {
  private RestaurantService service;
  private final IHandler handler;

  public EditRestaurant(RestaurantService service, IHandler handler){
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante: ");
    String name = handler.readLine();
    handler.writeLine("Nueva dirección del restaurante: ");
    String newAddress = handler.readLine();
    handler.writeLine("¿El restaurante está disponible? (Si/No)");
    Boolean available = handler.readLine().equalsIgnoreCase("Si");
    if(service.editRestaurant(service.getRestaurantByName(name), newAddress, available)){
      System.out.println("El restaurante ha sido editado: ");
      service.getRestaurantByName(name).displayRestaurant();
    }else{
      System.out.println("No se ha podido modificar el restaurante.");
    }
  }
}
