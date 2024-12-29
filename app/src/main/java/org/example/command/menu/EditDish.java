package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.models.Dish;
import org.example.services.DishService;

public class EditDish implements ICommand {
  private DishService service;
  private final IHandler handler;

  public EditDish(DishService service, IHandler handler) {
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante");
    String restaurantName = handler.readLine();
    handler.writeLine("Indica el nombre del plato a modificar: ");
    String dishName = handler.readLine();
    Dish dish= service.getDishByName(restaurantName, dishName);
    handler.writeLine("Indica el nuevo nombre del plato: ");
    String dishNewName = handler.readLine();
    handler.writeLine("Indica la nueva descripción del plato: ");
    String dishNewDescription = handler.readLine();
    handler.writeLine("Indica el nuevo precio del plato: ");
    Float dishNewPrice = Float.parseFloat(handler.readLine());
    if(service.editDish(restaurantName, dish, dishNewName, dishNewDescription, dishNewPrice)){
      System.out.println("Plato modificado exitosamente:");
      service.getDishByName(restaurantName,dishNewName).displayDish();
    }else {
      System.out.println("No se ha podido modificar el plato.");
    }
  }
}
