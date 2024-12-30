package org.example.services.utils;


import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidatorScanner implements IValidatorScanner {
    private final Scanner scanner;

    public ValidatorScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public  Integer integerScanner(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer después de leer un entero
                if (value >= 0) {
                    return value; // Devuelve el valor si es válido
                }
                System.out.println("Por favor, ingrese un número mayor o igual a 0.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpia el buffer después de una entrada inválida
            }
        }

    }
    @Override
    public Double doubleScanner(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                Double value = Double.parseDouble(scanner.nextLine());
                if (value >= 0) {
                    return value;
                }
                System.out.println("Por favor, ingrese un número mayor o igual a 0.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpia el buffer después de una entrada inválida
            }
        }

    }

    @Override
    public String stringScanner(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Entrada no válida. Por favor, ingrese un texto válido.");
        }
    }

    @Override
    public Float floatScanner(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    continue;
                }
                float value = Float.parseFloat(input);
                if (value < 0 || value > 5) {
                    System.out.println("Por favor, ingrese un número entre 0 y 5.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }



}