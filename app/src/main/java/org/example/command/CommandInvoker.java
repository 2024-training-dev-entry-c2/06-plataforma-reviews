package org.example.command;

import org.example.interfaces.ICommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandInvoker {
  private final Map<Integer, ICommand> commands = new LinkedHashMap<>();
  private final Map<Integer, String> commandDescriptions = new LinkedHashMap<>();

  public void registerCommand(int menuNumber, String description, ICommand command){
    commands.put(menuNumber, command);
    commandDescriptions.put(menuNumber, description);
  }

  public void excecuteCommand(int menuNumber){
    ICommand command = commands.get(menuNumber);
    if(command != null){
      command.execute();
    }else{
      System.out.println("Opción no válida.");
    }
  }

  public void printMenu(){
    for (Map.Entry<Integer, String> entry : commandDescriptions.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }
  }
}
