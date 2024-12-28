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
                return Float.parseFloat(scanner.nextLine());
            } catch (InputMismatchException e) {
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