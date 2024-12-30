package restaurant.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private final Scanner scanner;

    public ConsoleUtils() {
        this.scanner = new Scanner(System.in);
    }

    public ConsoleUtils(Scanner scanner) {
        this.scanner = scanner;
    }


    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public Integer getInteger(String message) {
        while (true) {
            try {
                System.out.println(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número con formato valido.");
            }
        }
    }

    public Double getDouble(String message) {
        while (true) {
            try {
                System.out.println(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número con formato valido.");
            }
        }
    }
}
