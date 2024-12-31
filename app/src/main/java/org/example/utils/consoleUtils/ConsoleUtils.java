// src/main/java/com/example/utils/consoleUtils/ConsoleUtils.java
package org.example.utils.consoleUtils;

import org.example.repositories.DishRepository;
import org.example.repositories.RestaurantRepository;

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

    public Object getObject(String prompt) {
        System.out.print(prompt);
        String type = scanner.nextLine();
        if (type.equalsIgnoreCase("Dish")) {
            String dishName = getString("Enter the name of the dish: ");
            return DishRepository.getInstance().findByName(dishName);
        } else if (type.equalsIgnoreCase("Restaurant")) {
            String restaurantName = getString("Enter the name of the restaurant: ");
            return RestaurantRepository.getInstance().findByName(restaurantName);
        } else {
            throw new IllegalArgumentException("Invalid type entered");
        }
    }

}