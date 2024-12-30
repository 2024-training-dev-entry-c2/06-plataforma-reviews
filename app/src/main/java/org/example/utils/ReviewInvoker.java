package org.example.utils;

import org.example.Interface.ICommand;

public class ReviewInvoker {
  private ICommand command;

  public void setCommand(ICommand command) {
    this.command = command;
  }

  public void executeCommand() {
    command.execute();
  }
}