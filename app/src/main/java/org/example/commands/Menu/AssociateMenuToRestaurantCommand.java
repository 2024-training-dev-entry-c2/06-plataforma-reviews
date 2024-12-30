package org.example.commands.Menu;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.MenuController;

public class AssociateMenuToRestaurantCommand implements ICommand {
  private final MenuController menuController;
  private final IConsoleHandler consoleHandler;

  public AssociateMenuToRestaurantCommand(MenuController menuController, IConsoleHandler consoleHandler) {
    this.menuController = menuController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingresa el nombre del restaurante:");
    String restaurantName = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese el nombre del menú:");
    String menuName = consoleHandler.readLine();
    menuController.associateMenuToRestaurant(restaurantName, menuName);
    consoleHandler.writeLine("Menú asociado con éxito al restaurante.");
  }
}
