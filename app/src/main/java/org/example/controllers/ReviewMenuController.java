package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.command.review.AddReview;
import org.example.interfaces.IController;
import org.example.observable.NotificationObserver;
import org.example.services.ReviewService;
import org.example.utils.ConsoleHandler;

public class ReviewMenuController implements IController {
  private final ReviewService service;
  private final CommandInvoker invoker;
  private final ConsoleHandler console;

  public ReviewMenuController(ConsoleHandler console) {
    this.service = new ReviewService();
    this.invoker = new CommandInvoker();
    this.console = console;
    registryCommands();
  }

  @Override
  public void start(){

    NotificationObserver observer = new NotificationObserver();
    service.addObserver(observer);

    while (true){
      System.out.println("\n-- Reseñas");
      invoker.printMenu();
      console.writeLine("Selecciona una opción (o ingresa 0 para regresar): ");
      int choice = Integer.parseInt(console.readLine());
      if(choice == 0){
        break;
      }
      invoker.excecuteCommand(choice);
    }
  }

  private void registryCommands(){
    invoker.registerCommand(1,"Agregar una reseña", new AddReview(service, console));
  }
}
