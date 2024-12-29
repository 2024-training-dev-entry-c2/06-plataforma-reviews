package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.models.Menu;
import org.example.services.MenuService;

public class ShowMenu implements ICommand {
  private MenuService service;
  private final IHandler handler;

  public ShowMenu(MenuService service, IHandler handler) {
    this.handler = handler;
    this.service = service;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante del cual deseas ver el menú: ");
    String restaurantName = handler.readLine();
    Menu menu = service.getMenuByRestaurantName(restaurantName);
    if(menu != null){
      menu.displayMenu();
    }else {
      System.out.println("No se encontró el restaurante o menú.");
    }
  }
}
