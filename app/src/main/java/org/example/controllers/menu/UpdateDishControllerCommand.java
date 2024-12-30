package org.example.controllers.menu;

import org.example.controllers.IControllerCommand;
import org.example.services.MenuService;
import org.example.utils.IHandler;

public class UpdateDishControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final MenuService menuService;

  public UpdateDishControllerCommand(IHandler handler, MenuService menuService) {
    this.handler = handler;
    this.menuService = menuService;
  }

  @Override
  public void execute() {
    this.menuService.displayMenus();
    handler.writeLine("Ingrese el ID del menu");
    Long menuId = Long.parseLong(handler.readLine());
    this.menuService.displayMenu(menuId);
    handler.writeLine("Ingrese el ID del plato");
    Long dishId = Long.parseLong(handler.readLine());
    handler.writeLine("Ingrese el nombre del plato");
    String name = handler.readLine();
    handler.writeLine("Ingrese la descripción");
    String description = handler.readLine();
    handler.writeLine("Ingrese el precio");
    String priceString = handler.readLine();
    Long price = null;
    if(!priceString.isEmpty()) {
      price = Long.parseLong(priceString);
    }
    this.menuService.updateDishMenu(dishId, name, description, price);
  }
}
