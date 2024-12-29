package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.review.AddReview;
import org.example.interfaces.IController;
import org.example.interfaces.IHandler;
import org.example.observable.NotificationObserver;
import org.example.services.ReviewService;

public class ReviewMenuController implements IController {
  private final ReviewService service;
  private final CommandInvoker invoker;
  private final IHandler handler;

  public ReviewMenuController(IHandler handler, CommandInvoker invoker) {
    this.service = new ReviewService();
    this.invoker = invoker;
    this.handler = handler;
    registryCommands();
  }

  @Override
  public void start(){

    NotificationObserver observer = new NotificationObserver();
    service.addObserver(observer);

    while (true){
      System.out.println("\n-- Reseñas");
      invoker.printMenu();
      handler.writeLine("Selecciona una opción (o ingresa 0 para regresar): ");
      int choice = Integer.parseInt(handler.readLine());
      if(choice == 0){
        break;
      }
      invoker.executeCommand(choice);
    }
  }

  private void registryCommands(){
    invoker.registerCommand(1,"Agregar una reseña", new AddReview(service, handler));
  }
}
