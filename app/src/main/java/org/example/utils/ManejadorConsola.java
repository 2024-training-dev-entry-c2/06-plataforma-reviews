package org.example.utils;

import java.io.InputStream;
import java.util.Scanner;

public class ManejadorConsola implements IManejadorConsola{

    private final Scanner scanner;

    public ManejadorConsola() {
        this.scanner = new Scanner(System.in);
    }


    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }
}
