package org.example.utils;

import java.util.Scanner;

public class Validator {
  private final Scanner scanner;

  public Validator(Scanner scanner) {
    this.scanner = scanner;
  }

  public String readString(String message) {
    System.out.println(message);
    return scanner.nextLine();
  }

  public void printMessage(String message) {
    System.out.println(message);
  }

  public Integer readInteger(String message) {
    int input;
    try {
      System.out.println(message);
      input = Integer.parseInt(scanner.nextLine());
      return input;
    } catch (NumberFormatException e) {
      System.err.println("Entrada inválida. Por favor ingrese un número entero.");
      return readInteger(message);
    }
  }

  public Float readFloat(String message) {
    float input;
    try {
      System.out.println(message);
      input = Float.parseFloat(scanner.nextLine());
      return input;
    } catch (NumberFormatException e) {
      System.err.println("Entrada inválida. Por favor ingrese un número real.");
      return readFloat(message);
    }
  }

  public Float readRating(String message) {
    float input = readFloat(message);

    if (validateRange(input)) {
      System.err.println("Entrada inválida. Por favor ingrese un valor entre 0 y 5.");
      return readRating(message);
    }

    return input;
  }

  private boolean validateRange(float input) {
		return input < 0 || input > 5;
	}
}