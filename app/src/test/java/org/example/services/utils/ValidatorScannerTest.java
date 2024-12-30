package org.example.services.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorScannerTest {

    @Test
    void testIntegerScanner_ValidInput() {
        Scanner mockScanner = new Scanner("10\n");
        ValidatorScanner validatorScanner = new ValidatorScanner(mockScanner);

        int result = validatorScanner.integerScanner("Ingrese un número:");
        assertEquals(10, result);
    }

    @Test
    void testStringScanner_ValidInput() {
        Scanner mockScanner = new Scanner("hello\n");
        ValidatorScanner validatorScanner = new ValidatorScanner(mockScanner);

        String result = validatorScanner.stringScanner("Ingrese texto:");
        assertEquals("hello", result);
    }

    @Test
    void testFloatScanner_ValidInput() {
        Scanner mockScanner = new Scanner("3.5\n");
        ValidatorScanner validatorScanner = new ValidatorScanner(mockScanner);

        float result = validatorScanner.floatScanner("Ingrese un número:");
        assertEquals(3.5f, result);
    }

    @Test
    void testFloatScanner_OutOfRange() {
        Scanner mockScanner = new Scanner("6\n3.5\n");

        ValidatorScanner validatorScanner = new ValidatorScanner(mockScanner);
        float result = validatorScanner.floatScanner("Ingrese un número:");
        assertEquals(3.5f, result);
    }
    @Test
    void testDoubleScanner_ValidInput() {
        // Entrada simulada
        String simulatedInput = "3.14\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        ValidatorScanner validatorScanner = new ValidatorScanner(scanner);

        // Ejecución
        Double result = validatorScanner.doubleScanner("Ingrese un número:");

        // Validación
        assertEquals(3.14, result);
    }


}