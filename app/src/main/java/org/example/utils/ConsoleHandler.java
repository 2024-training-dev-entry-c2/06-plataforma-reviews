package org.example.utils;

import org.example.Interface.IConsoleHandler;

import java.util.Scanner;

public class ConsoleHandler implements IConsoleHandler {

  private final Scanner scanner;

  public ConsoleHandler() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public String readLine() {
    return scanner.nextLine();
  }

  @Override
  public void writeLine(String message) {
    System.out.println(message);
  }
}