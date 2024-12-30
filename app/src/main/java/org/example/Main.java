package org.example;

import org.example.utils.AppMenu;
import org.example.utils.ConsoleHandler;
import org.example.Interface.IConsoleHandler;
import org.example.utils.HandleOption;

public class Main {
  public static boolean keepRunning = true;

  private final AppMenu appMenu;
  private final HandleOption handleOption;
  private final IConsoleHandler consoleHandler;

  public Main(AppMenu appMenu, HandleOption handleOption, IConsoleHandler consoleHandler) {
    this.appMenu = appMenu;
    this.handleOption = handleOption;
    this.consoleHandler = consoleHandler;
  }

  public void run() {
    while (keepRunning) {
      appMenu.displayMenu();
      Integer option = Integer.parseInt(consoleHandler.readLine());
      handleOption.execute(option);
    }
  }

  public static void main(String[] args) {
    IConsoleHandler consoleHandler = new ConsoleHandler();
    AppMenu appMenu = new AppMenu(consoleHandler);
    HandleOption handleOption = new HandleOption(consoleHandler);

    Main mainApp = new Main(appMenu, handleOption, consoleHandler);
    mainApp.run();
  }
}