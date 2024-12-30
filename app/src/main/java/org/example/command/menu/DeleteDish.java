package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.models.Dish;
import org.example.services.DishService;

public class DeleteDish implements ICommand {
  private final DishService service;
  private final IHandler handler;

  public DeleteDish(DishService service, IHandler handler) {
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("Indica el nombre del restaurante");
    String restaurantName = handler.readLine();
    handler.writeLine("Indica el nombre del plato a eliminar: ");
    String dishName = handler.readLine();
    Dish dish= service.getDishByName(restaurantName, dishName);
    if(service.removeDish(restaurantName, dish)){
      System.out.println("¡Plato eliminado exitosamente!");
    }else{
      System.out.println("No se ha podido eliminar el plato.");
    }
  }
}
