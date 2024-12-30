package org.example.utils;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.commands.CreateReviewCommand;
import org.example.constants.MenuOption;

import static org.example.utils.AppMenu.commandMap;

public class HandleOption {
  ReviewFactory reviewFactory;
  private final IConsoleHandler consoleHandler;

  public HandleOption(IConsoleHandler consoleHandler) {
    this.consoleHandler = consoleHandler;
    this.reviewFactory = new ReviewFactory();
  }

  public void execute(Integer option) {
    try {
      MenuOption menuOption = MenuOption.fromInt(option);
      if (menuOption == MenuOption.ADD_REVIEW_TO_DISH || menuOption == MenuOption.ADD_REVIEW_TO_RESTAURANT) {
        handleReviewOption(menuOption);
      } else if (menuOption == MenuOption.EXIT) {
        handleExitOption();
      } else {
        handleCommandOption(menuOption);
      }
    } catch (IllegalArgumentException e) {
      consoleHandler.writeLine(e.getMessage());
    }
  }

  private void handleReviewOption(MenuOption menuOption) {
    ICommand command = new CreateReviewCommand(consoleHandler, reviewFactory, menuOption);
    command.execute();
  }

  private void handleExitOption() {
    consoleHandler.writeLine("Saliendo...");
    System.exit(0);
  }

  private void handleCommandOption(MenuOption menuOption) {
    ICommand command = commandMap.get(menuOption);
    if (command != null) {
      command.execute();
    } else {
      throw new IllegalArgumentException("Opcion no encontrada");
    }
  }
}