package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.menu.AddDish;
import org.example.command.menu.AddMenu;
import org.example.command.restaurant.CreateRestaurant;
import org.example.command.restaurant.DeleteRestaurant;
import org.example.command.restaurant.EditRestaurant;
import org.example.interfaces.IController;
import org.example.services.DishService;
import org.example.services.MenuService;
import org.example.utils.ConsoleHandler;

public class MenuMenuController implements IController {
  private final MenuService menuService;
  private final DishService dishService;
  private final CommandInvoker invoker;
  private final ConsoleHandler console;

  public MenuMenuController(ConsoleHandler console) {
    this.menuService = new MenuService();
    this.dishService = new DishService();
    this.invoker = new CommandInvoker();
    this.console = console;
    registryCommands();
  }

  @Override
  public void start(){
    while (true){
      System.out.println("\n-- Menú");
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
    invoker.registerCommand(1,"Agregar un menú a un restaurante", new AddMenu(menuService, console));
    invoker.registerCommand(2,"Agregar un plato", new AddDish(dishService, console));
  }

}
