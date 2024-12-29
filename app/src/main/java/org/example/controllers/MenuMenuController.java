package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.menu.AddDish;
import org.example.command.menu.AddMenu;
import org.example.interfaces.IController;
import org.example.interfaces.IHandler;
import org.example.services.DishService;
import org.example.services.MenuService;

public class MenuMenuController implements IController {
  private final MenuService menuService;
  private final DishService dishService;
  private final CommandInvoker invoker;
  private final IHandler handler;

  public MenuMenuController(IHandler handler, CommandInvoker invoker) {
    this.menuService = new MenuService();
    this.dishService = new DishService();
    this.invoker = invoker;
    this.handler = handler;
    registryCommands();
  }

  @Override
  public void start(){
    while (true){
      System.out.println("\n-- Menú");
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
    invoker.registerCommand(1,"Agregar un menú a un restaurante", new AddMenu(menuService, handler));
    invoker.registerCommand(2,"Agregar un plato", new AddDish(dishService, handler));
  }

}
