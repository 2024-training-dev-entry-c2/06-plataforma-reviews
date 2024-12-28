package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.services.MenuService;
import org.example.utils.ConsoleHandler;

public class AddMenu implements ICommand {
  private MenuService service;
  private final ConsoleHandler console;

  public AddMenu(MenuService service, ConsoleHandler console) {
    this.console = console;
    this.service = service;
  }

  @Override
  public void execute() {
    console.writeLine("Indica el nombre del restaurante al cual deseas añadir el menú: ");
    String restaurantName = console.readLine();
    console.writeLine("Ingresa el nombre del menú: ");
    String menuName = console.readLine();
    if(service.addMenu(restaurantName, menuName)){
      System.out.println("¡Se agregado el menú al restaurante!");
    }else{
      System.out.println("No se ha podido agregar el menú.");
    }
  }
}
