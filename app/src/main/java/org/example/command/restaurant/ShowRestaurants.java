package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.example.utils.ConsoleHandler;

public class ShowRestaurants implements ICommand {
  private RestaurantService service;
  private final ConsoleHandler console;

  public ShowRestaurants(RestaurantService service, ConsoleHandler console){
    this.service = service;
    this.console = console;
  }


  @Override
  public void execute() {
    service.getRestaurants().forEach(Restaurant::displayRestaurant);
  }
}
