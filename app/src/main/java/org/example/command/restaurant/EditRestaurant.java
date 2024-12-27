package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.services.RestaurantService;
import org.example.utils.ConsoleHandler;

public class EditRestaurant implements ICommand {
  private RestaurantService service;
  private final ConsoleHandler console;

  public EditRestaurant(RestaurantService service, ConsoleHandler console){
    this.service = service;
    this.console = console;
  }

  @Override
  public void execute() {
    console.writeLine("Indica el nombre del restaurante: ");
    String name = console.readLine();
    console.writeLine("Nuevo nombre del restaurante: ");
    String newName = console.readLine();
    console.writeLine("Nueva dirección del restaurante: ");
    String newAddress = console.readLine();
    service.editRestaurant(service.getRestaurantByName(name), newName, newAddress);
  }
}
