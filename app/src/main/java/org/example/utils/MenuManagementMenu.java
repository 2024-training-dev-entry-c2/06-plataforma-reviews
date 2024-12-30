package org.example.utils;

import org.example.controllers.IControllerCommand;
import org.example.controllers.menu.AddDishControllerCommand;
import org.example.controllers.menu.RemoveDishControllerCommand;
import org.example.controllers.menu.UpdateDishControllerCommand;
import org.example.services.MenuService;

import java.util.Map;

public class MenuManagementMenu implements IMenuCommand {
  private final IHandler handler;
  private final MenuService menuService;
  private Map<Integer, IControllerCommand> controllers ;
  private Boolean exit = false;

  public MenuManagementMenu(MenuService menuService, IHandler handler) {
    this.menuService = menuService;
    this.handler = handler;
    this.controllers = Map.of(
      1, new AddDishControllerCommand(handler, menuService),
      2, new UpdateDishControllerCommand(handler, menuService),
      3, new RemoveDishControllerCommand(handler, menuService),
      4, ()-> {
        handler.writeLine("Saliendo de opciones de menu");
        exit = true;
      }
    );
  }

  public void displayMenu() {
    String message = "\nSeleccione una opción: \n1. Añadir plato\n2. Editar plato\n3. Eliminar plato\n4. Menú principal";
    while (!exit) {
      handler.writeLine(message);
      int choice = Integer.parseInt(handler.readLine());
      if (choice >= 1 && choice <= 4) {
        controllers.get(choice).execute();
      } else {
        handler.writeLine("Opción inválida");
      }
    }
    exit = false;
  }
}
