package org.example.command.menu;

import org.example.interfaces.ICommand;
import org.example.services.DishService;
import org.example.utils.ConsoleHandler;

public class AddDish implements ICommand {
  private DishService service;
  private final ConsoleHandler console;

  public AddDish(DishService service, ConsoleHandler console) {
    this.service = service;
    this.console = console;
  }

  @Override
  public void execute() {
    console.writeLine("Indica el nombre del restaurante");
    String restaurantName = console.readLine();
    console.writeLine("Indica el nombre del plato: ");
    String dishName = console.readLine();
    console.writeLine("Indica la descripción del plato: ");
    String dishDescription = console.readLine();
    console.writeLine("Indica el precio del plato: ");
    Float dishPrice = Float.parseFloat(console.readLine());
    if(service.addDish(restaurantName, dishName, dishDescription, dishPrice)){
      System.out.println("¡Se añadió el plato al menú del restaurante!");
    }else{
      System.out.println("No se ha podido añadir el plato.");
    }
  }
}
