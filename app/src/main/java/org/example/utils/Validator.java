package org.example.utils;

import java.time.LocalDate;
import java.time.LocalTime;
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
      System.err.println("Entrada inválida. Por favor ingrese un número entero.");
      return readFloat(message);
    }
  }

  public LocalDate readLocalDate(String message) {
    LocalDate date;
    try {
      System.out.println(message);
      date = LocalDate.parse(scanner.nextLine());

      if (date.isBefore(LocalDate.now())) {
        System.err.println("La fecha de inicio no puede ser anterior a la fecha actual. Inténtelo nuevamente.");
        return readLocalDate(message);
      }

      return date;
    } catch (Exception e) {
      System.err.println("Fecha inválida. El formato debe ser: yyyy-MM-dd");
      return readLocalDate(message);
    }
  }

  public LocalDate readLocalDate(String message, LocalDate startDate) {
    LocalDate date;
    try {
      System.out.println(message);
      date = LocalDate.parse(scanner.nextLine());

      if (date.isBefore(startDate)) {
        System.err.println("La fecha de inicio no puede ser anterior a la fecha actual. Inténtelo nuevamente.");
        return readLocalDate(message);
      }

      return date;
    } catch (Exception e) {
      System.err.println("Fecha inválida. El formato debe ser: yyyy-MM-dd");
      return readLocalDate(message);
    }
  }

  public LocalTime readLocalTime(String message) {
    LocalTime time;
    try {
      System.out.println(message);
      time = LocalTime.parse(scanner.nextLine());
      return time;
    } catch (Exception e) {
      System.err.println("Hora inválida. El formato debe ser: HH:mm");
      return readLocalTime(message);
    }
  }
}