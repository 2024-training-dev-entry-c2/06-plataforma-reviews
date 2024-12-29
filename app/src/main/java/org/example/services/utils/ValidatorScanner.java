package org.example.services.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidatorScanner implements IValidatorScanner {
    private static Scanner scanner;

    public ValidatorScanner() {
        scanner = new Scanner(System.in);
    }

    @Override
    public  Integer integerScanner(String prompt) {
        try {
            System.out.println(prompt);
            int value = scanner.nextInt();
            return value < 0 ? this.integerScanner(prompt) : value ;
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            return integerScanner(prompt);
        }

    }
    @Override
    public Double doubleScanner(String prompt) {
        try {
            System.out.println(prompt);
            Double value = scanner.nextDouble();
            return value < 0 ? this.doubleScanner(prompt) : value ;
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            return doubleScanner(prompt);
        }

    }

    @Override
    public String stringScanner(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return scanner.next();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                return stringScanner(prompt);
            }
        }
    }

    @Override
    public Float floatScanner(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scanner.nextLine().trim(); // Lee y elimina espacios en blanco
                if (input.isEmpty()) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    continue;
                }
                float value = Float.parseFloat(input); // Intenta convertir a float
                if (value < 0 || value > 5) { // Validación de rango
                    System.out.println("Por favor, ingrese un número entre 0 y 5.");
                    continue;
                }
                return value; // Retorna el valor válido
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }

    @Override
    public LocalDate dateScanner(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                return dateScanner(prompt);
            }
        }
    }

    @Override
    public void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}