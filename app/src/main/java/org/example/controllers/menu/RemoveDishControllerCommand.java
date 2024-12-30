package org.example.controllers.menu;

import org.example.controllers.IControllerCommand;
import org.example.services.MenuService;
import org.example.utils.IHandler;

public class RemoveDishControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final MenuService menuService;

  public RemoveDishControllerCommand(IHandler handler, MenuService menuService) {
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
    this.menuService.removeDishMenu(dishId, menuId);
  }
}
