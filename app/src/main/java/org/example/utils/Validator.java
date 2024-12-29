package org.example.utils;

import java.util.Map;
import java.util.Scanner;

public class Validator {
    private final Scanner scanner;

    public Validator(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input;

    }

    public Integer readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);

    }

    public Float readFloat(String prompt) {
        float input;
        try {
            System.out.print(prompt);
            input = Float.parseFloat(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            System.err.println("Error: Debe ingresar un número decimal.");
            return readFloat(prompt);
        }
    }

    public Boolean readBoolean(String prompt) {
        Map<String, Boolean> responses = Map.of(
                "s", true, "si", true, "y", true, "yes", true,
                "n", false, "no", false
        );
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        Boolean result = responses.get(input);
        return result;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
