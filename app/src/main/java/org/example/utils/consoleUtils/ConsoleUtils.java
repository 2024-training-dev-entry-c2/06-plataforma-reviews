// src/main/java/com/example/utils/consoleUtils/ConsoleUtils.java
package org.example.utils.consoleUtils;

import java.util.Scanner;

public class ConsoleUtils {
    private final Scanner scanner;

    public ConsoleUtils() {
        this.scanner = new Scanner(System.in);
    }

    public int getInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número decimal.");
            }
        }
    }

}