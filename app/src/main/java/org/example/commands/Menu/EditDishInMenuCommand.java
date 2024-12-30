package org.example.commands.Menu;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;

import org.example.controllers.MenuController;
import org.example.models.DishModel;

public class EditDishInMenuCommand implements ICommand {
  private final MenuController menuController;
  private final IConsoleHandler consoleHandler;

  public EditDishInMenuCommand(MenuController menuController, IConsoleHandler consoleHandler) {
    this.menuController = menuController;
    this.consoleHandler = consoleHandler;

  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingresa el nombre del restaurante:");
    String restaurantName = consoleHandler.readLine();
    consoleHandler.writeLine("Ingresa el nombre del plato:");
    String dishName = consoleHandler.readLine();
    consoleHandler.writeLine("Ingresa el nuevo nombre del plato:");
    String updatedDishName = consoleHandler.readLine();
    consoleHandler.writeLine("Ingresa la nueva descripción del plato:");
    String updatedDishDescription = consoleHandler.readLine();
    consoleHandler.writeLine("Ingresa el nuevo precio del plato:");
    Double updatedDishPrice = Double.parseDouble(consoleHandler.readLine());
    menuController.editDishInMenu(restaurantName, dishName, new DishModel(updatedDishName, updatedDishDescription, updatedDishPrice));
    consoleHandler.writeLine("Plato editado con éxito en el menú.");
  }
}
