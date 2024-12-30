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
        String possibleDate = readLine(message);
        LocalDate date = null;
        try{
            date = LocalDate.parse(possibleDate);
        } catch (Exception e) {
            writeLine("-- Introduzca una fecha con formato valido (yyyy-MM-dd)");
            readDate(message);
        }
        return date;
    }

    @Override
    public Double readDouble(String message) {
        writeLine(message);
        double value = 0;
        try{
            value =  scanner.nextDouble();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            writeLine("-- Introduzca un numero valido");
            readDouble(message);
        }
        return value;
    }

    @Override
    public Boolean readBooleanYesOrNo(String message) {
        writeLine(message);
        return scanner.nextLine().equalsIgnoreCase("si");

    }

    @Override
    public Float readFloat(String message) {
        writeLine(message);
        float value = 0.0F;
        try{
            value =  scanner.nextFloat();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            writeLine("-- Introduzca un numero valido");
            readFloat(message);
        }
        return value;
    }
}
