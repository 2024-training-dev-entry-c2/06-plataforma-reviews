package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.restaurant.CreateRestaurant;
import org.example.command.restaurant.DeleteRestaurant;
import org.example.command.restaurant.EditRestaurant;
import org.example.command.restaurant.ShowRestaurants;
import org.example.interfaces.IController;
import org.example.interfaces.IHandler;
import org.example.services.RestaurantService;

public class RestaurantMenuController implements IController {
  private final RestaurantService service;
  private final CommandInvoker invoker;
  private final IHandler handler;

  public RestaurantMenuController(IHandler handler, CommandInvoker invoker) {
    this.service = new RestaurantService();
    this.invoker = invoker;
    this.handler = handler;
    registryCommands();
  }

  @Override
  public void start(){
    while (true){
      System.out.println("\n-- Restaurante");
      invoker.printMenu();
      handler.writeLine("Selecciona una opción (o ingresa 0 para regresar): ");
      int choice = Integer.parseInt(handler.readLine());
      if(choice == 0){
        break;
      }
      invoker.executeCommand(choice);
    }
  }

  private void registryCommands(){
    invoker.registerCommand(1,"Crear un restaurante", new CreateRestaurant(service, handler));
    invoker.registerCommand(2,"Modificar un restaurante", new EditRestaurant(service, handler));
    invoker.registerCommand(3,"Eliminar un restaurante", new DeleteRestaurant(service, handler));
    invoker.registerCommand(4,"Mostrar los restaurantes", new ShowRestaurants(service));
  }

  public CommandInvoker getInvoker() {
    return invoker;
  }
}
