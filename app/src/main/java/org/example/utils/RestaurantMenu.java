package org.example.utils;

import org.example.controllers.IControllerCommand;
import org.example.controllers.restaurant.AddRestaurantControllerCommand;
import org.example.controllers.restaurant.DisplayRestaurantsControllerCommand;
import org.example.controllers.restaurant.RemoveRestaurantControllerCommand;
import org.example.controllers.restaurant.UpdateRestaurantControllerCommand;
import org.example.services.RestaurantService;

import java.util.Map;

public class RestaurantMenu implements IMenuCommand {
  private final IHandler handler;
  private final RestaurantService restaurantService;
  private Map<Integer, IControllerCommand> controllers ;
  private Boolean exit = false;

  public RestaurantMenu(RestaurantService restaurantService, IHandler handler) {
    this.restaurantService = restaurantService;
    this.handler = handler;
    this.controllers = Map.of(
      1, new AddRestaurantControllerCommand(handler, restaurantService),
      2, new UpdateRestaurantControllerCommand(handler, restaurantService),
      3, new DisplayRestaurantsControllerCommand(restaurantService),
      4, new RemoveRestaurantControllerCommand(handler, restaurantService),
      5, ()-> {
        handler.writeLine("Saliendo de opciones de restaurante");
        exit = true;
      }
    );
  }

  public void displayMenu() {
    String message = "\nSeleccione una opción:\n1. Añadir restaurante\n2. Actualizar restaurante\n3. Mostrar restaurantes\n4. Eliminar restaurante\n5. Menú principal";
    while (!exit) {
      handler.writeLine(message);
      int choice = Integer.parseInt(handler.readLine());
      if (choice >= 1 && choice <= 5) {
        controllers.get(choice).execute();      }
      else {
        handler.writeLine("Opción inválida");
      }
    }
    exit = false;
  }

}