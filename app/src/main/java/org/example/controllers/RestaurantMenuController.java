package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.restaurant.CreateRestaurant;
import org.example.command.restaurant.DeleteRestaurant;
import org.example.command.restaurant.EditRestaurant;
import org.example.command.restaurant.ShowRestaurants;
import org.example.interfaces.IController;
import org.example.services.RestaurantService;
import org.example.utils.ConsoleHandler;

public class RestaurantMenuController implements IController {
  private final RestaurantService service;
  private final CommandInvoker invoker;
  private final ConsoleHandler console;

  public RestaurantMenuController(ConsoleHandler console) {
    this.service = new RestaurantService();
    this.invoker = new CommandInvoker();
    this.console = console;
    registryCommands();
  }

  @Override
  public void start(){
    while (true){
      System.out.println("\n-- Restaurante");
      invoker.printMenu();
      console.writeLine("Selecciona una opción (o ingresa 0 para regresar): ");
      int choice = Integer.parseInt(console.readLine());
      if(choice == 0){
        break;
      }
      invoker.excecuteCommand(choice);
    }
  }

  private void registryCommands(){
    invoker.registerCommand(1,"Crear un restaurante", new CreateRestaurant(service, console));
    invoker.registerCommand(2,"Modificar un restaurante", new EditRestaurant(service, console));
    invoker.registerCommand(3,"Eliminar un restaurante", new DeleteRestaurant(service, console));
    invoker.registerCommand(4,"Mostrar los restaurantes", new ShowRestaurants(service));
  }
}
