package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.example.utils.ConsoleHandler;

public class CreateRestaurant implements ICommand {
  private RestaurantService service;
  private final ConsoleHandler console;

  public CreateRestaurant(RestaurantService service, ConsoleHandler console){
    this.service = service;
    this.console = console;
  }


  @Override
  public void execute() {
    console.writeLine("Indica el nombre del restaurante: ");
    String name = console.readLine();
    console.writeLine("Indica la dirección del restaurante: ");
    String address = console.readLine();
    service.createRestaurant(new Restaurant(name, address));
  }
}
