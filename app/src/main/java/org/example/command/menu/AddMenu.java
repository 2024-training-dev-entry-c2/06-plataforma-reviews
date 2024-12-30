package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.services.MenuService;

public class AddMenu implements ICommand {
  private final MenuService service;
  private final IHandler handler;

  public AddMenu(MenuService service, IHandler handler) {
    this.handler = handler;
    this.service = service;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante al cual deseas añadir el menú: ");
    String restaurantName = handler.readLine();
    handler.writeLine("Ingresa el nombre del menú: ");
    String menuName = handler.readLine();
    if(service.addMenu(restaurantName, menuName)){
      System.out.println("¡Se agregado el menú al restaurante!");
    }else{
      System.out.println("No se ha podido agregar el menú.");
    }
  }
}
