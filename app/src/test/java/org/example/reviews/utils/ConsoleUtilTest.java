package org.example.reviews.utils;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.mockito.Mockito.*;


class ConsoleUtilTest {

    private ConsoleUtil consoleUtil;
    private Scanner mockScanner;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockScanner = mock(Scanner.class);

        consoleUtil = new ConsoleUtil() {
            @Override
            public String readLine(String message) {
                return mockScanner.nextLine();
            }

            @Override
            public Integer readInt(String message) {
                return mockScanner.nextInt();
            }

            @Override
            public Double readDouble(String message) {
                return mockScanner.nextDouble();
            }

            @Override
            public LocalDate readDate(String message) {
                return LocalDate.parse("2024-12-31");
            }

            @Override
            public Boolean readBooleanYesOrNo(String message) {
                return mockScanner.nextLine().equalsIgnoreCase("si");
            }

            @Override
            public Float readFloat(String message) {
                return mockScanner.nextFloat();
            }
        };
    }

    @Test
    void testWriteLine() {
        String message = "Hello, world!";
        consoleUtil.writeLine(message);

        verifySystemOutContains(message);
    }

    @Test
    void testReadLine() {
        when(mockScanner.nextLine()).thenReturn("prueba de entrada");

        String result = consoleUtil.readLine("esto es una linea:");

        verify(mockScanner).nextLine();
        assert(result.equals("Test input"));
    }

    @Test
    void testReadInt() {
        when(mockScanner.nextInt()).thenReturn(42);
        when(mockScanner.nextLine()).thenReturn("");

        Integer result = consoleUtil.readInt("ingresa un numero:");

        verify(mockScanner).nextInt();
        assert(result.equals(42));
    }

    @Test
    void testReadInt_InvalidInput() {
        when(mockScanner.nextInt()).thenThrow(new NumberFormatException("introduzca un numero valido"));
        when(mockScanner.nextLine()).thenReturn("");

        Integer result = consoleUtil.readInt("ingresa un numero:");

        verify(mockScanner).nextInt();
        assert(result.equals(0));
    }

    @Test
    void testReadDouble() {
        when(mockScanner.nextDouble()).thenReturn(3.14);
        when(mockScanner.nextLine()).thenReturn("");

        Double result = consoleUtil.readDouble("ingresa un numero:");

        verify(mockScanner).nextDouble();
        assert(result.equals(3.14));
    }

    @Test
    void testReadBooleanYesOrNo() {
        when(mockScanner.nextLine()).thenReturn("si");
        Boolean result = consoleUtil.readBooleanYesOrNo("deseas agregar el plato?");

        verify(mockScanner).nextLine();
        assert(result);
    }

    @Test
    void testReadBooleanYesOrNo_InvalidInput() {
        when(mockScanner.nextLine()).thenReturn("no");
        Boolean result = consoleUtil.readBooleanYesOrNo("deseas agregar el plato?");

        verify(mockScanner).nextLine();
        assert(!result);
    }

    @Test
    void testReadFloat() {
        when(mockScanner.nextFloat()).thenReturn(2.5F);
        when(mockScanner.nextLine()).thenReturn("");

        Float result = consoleUtil.readFloat("Ingresa un numero flotante:");

        verify(mockScanner).nextFloat();
        assert(result.equals(2.5F));
    }

    private void verifySystemOutContains(String expectedOutput) {
        String actualOutput = outputStream.toString().trim();
        assert(actualOutput.contains(expectedOutput));
    }
}

