package org.example.reviews.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUtil {
    private Scanner scanner;

    public ConsoleUtil() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void writeLine(String message) {
        System.out.println(message);
    }

    public Integer readInt(String message){
        int value = 0;
        try{
            value =  Integer.parseInt(message);
        } catch (NumberFormatException e) {
            writeLine("Introduzca un numero valido");
            readInt(message);
        }
        return value;
    }
    public LocalDate readDate(String message){
        LocalDate date = null;
        try{
            date = LocalDate.parse(message);
        } catch (Exception e) {
            writeLine("Introduzca una fecha con formato valido (yyyy-MM-dd)");
            readDate(message);
        }
        return date;
    }
}
