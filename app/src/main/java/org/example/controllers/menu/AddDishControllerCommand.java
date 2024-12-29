package org.example.controllers.menu;

import org.example.controllers.IControllerCommand;
import org.example.services.MenuService;
import org.example.utils.IHandler;

public class AddDishControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final MenuService menuService;

  public AddDishControllerCommand(IHandler handler, MenuService menuService) {
    this.handler = handler;
    this.menuService = menuService;
  }

  @Override
  public void execute() {
    this.menuService.displayMenus();
    handler.writeLine("Ingrese el ID del menu");
    Long menuId = Long.parseLong(handler.readLine());
    handler.writeLine("Ingrese el nombre del plato");
    String name = handler.readLine();
    handler.writeLine("Ingrese la descripción");
    String description = handler.readLine();
    handler.writeLine("Ingrese el precio");
    Long price = Long.parseLong(handler.readLine());
    this.menuService.addDishMenu(menuId, name, description, price );
  }
}
