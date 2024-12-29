package org.example.reviews.utils;

import org.example.reviews.utils.interfaces.IConsole;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUtil implements IConsole {
    private Scanner scanner;

    public ConsoleUtil() {
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void writeLine(String message) {
        System.out.printf("%s \n", message);
    }

    @Override
    public String readLine(String message) {
        writeLine(message);
        return scanner.nextLine();
    }

    @Override
    public Integer readInt(String message){
        writeLine(message);
        int value = 0;
        try{
            value =  scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            writeLine("-- Introduzca un numero valido");
            readInt(message);
        }
        return value;
    }

    @Override
    public LocalDate readDate(String message){
        writeLine(message);
        LocalDate date = null;
        try{
            date = LocalDate.parse(message);
        } catch (Exception e) {
            writeLine("-- Introduzca una fecha con formato valido (yyyy-MM-dd)");
            readDate(message);
        }
        return date;
    }
}
