package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.services.DishService;

public class AddDish implements ICommand {
  private DishService service;
  private final IHandler handler;

  public AddDish(DishService service, IHandler handler) {
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante");
    String restaurantName = handler.readLine();
    handler.writeLine("Indica el nombre del plato: ");
    String dishName = handler.readLine();
    handler.writeLine("Indica la descripción del plato: ");
    String dishDescription = handler.readLine();
    handler.writeLine("Indica el precio del plato: ");
    Float dishPrice = Float.parseFloat(handler.readLine());
    if(service.addDish(restaurantName, dishName, dishDescription, dishPrice)){
      System.out.println("¡Se añadió el plato al menú del restaurante!");
    }else{
      System.out.println("No se ha podido añadir el plato.");
    }
  }
}
