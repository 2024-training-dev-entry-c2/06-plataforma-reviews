package org.example.utils;

import org.example.interfaces.IController;
import org.example.interfaces.IHandler;

import java.util.Map;

public class ReviewerMenu {
  private final IHandler handler;
  private final Map<Integer, IController> controllers;

  public ReviewerMenu(IHandler handler, IController restaurantController, IController menuController, IController reviewController) {
    this.handler = handler;
    this.controllers = Map.of(
      1, restaurantController,
      2, menuController,
      3, reviewController
    );
  }

  public void displayMenu(){
    handler.writeLine("\n¡Bienvenido al reseñador de Restaurantes!");

    int choice;

    do{
      handler.writeLine("¿Qué deseas hacer?\n1. Ver opciones de restaurante.\n2. Ver opciones de menú.\n3. Ver opciones de reseña.\n0. Salir");
      choice = Integer.parseInt(handler.readLine());
      if(choice != 0) {
        controllers.get(choice).start();
      }else{
        handler.writeLine("\n¡Gracias por usar nuestros servicios!");
      }
    } while (choice != 0);
  }
}
