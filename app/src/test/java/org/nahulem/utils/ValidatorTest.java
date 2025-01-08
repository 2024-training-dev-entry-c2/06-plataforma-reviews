package org.nahulem.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ValidatorTest {
    private Scanner mockScanner;
    private Validator validator;

    @BeforeEach
    void setUp() {
        mockScanner = mock(Scanner.class);
        validator = new Validator(mockScanner);
    }

    @Test
    void readString() {
        when(mockScanner.nextLine()).thenReturn("Test");

        String result = validator.readString("Test");

        assertEquals("Test", result);

        verify(mockScanner).nextLine();
    }

    @Test
    void readInt() {
        when(mockScanner.nextLine()).thenReturn("1");

        Integer result = validator.readInt("1");

        assertEquals(1, result);

        verify(mockScanner).nextLine();
    }

    @Test
    void readWrongInt() {
        when(mockScanner.nextLine()).thenReturn("a", "1");

        Integer result = validator.readInt("1");

        assertEquals(1, result);

        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void readFloat() {
        when(mockScanner.nextLine()).thenReturn("1.0");

        Float result = validator.readFloat("1.0");

        assertEquals(1.0f, result);

        verify(mockScanner).nextLine();
    }

    @Test
    void readWrongFloat() {
        when(mockScanner.nextLine()).thenReturn("a", "1.0");

        Float result = validator.readFloat("1.0");

        assertEquals(1.0f, result);

        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void readBoolean() {
        when(mockScanner.nextLine()).thenReturn("si");

        Boolean result = validator.readBoolean("Ingrese S/N:");

        assertTrue(result);
        verify(mockScanner).nextLine();
    }

    @Test
    void readBooleanInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("invalid", "no");

        Boolean result = validator.readBoolean("Ingrese S/N:");

        assertFalse(result);
        verify(mockScanner, times(2)).nextLine();
    }


    @Test
    void readRating() {
        when(mockScanner.nextLine()).thenReturn("5.0");

        Float result = validator.readRating("5.0");

        assertEquals(5.0f, result);

        verify(mockScanner).nextLine();
    }

    @Test
    void readWrongRatingBelow() {
        when(mockScanner.nextLine()).thenReturn("-1.0", "5.0");

        Float result = validator.readRating("5.0");

        assertEquals(5.0f, result);

        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void readWrongRatingAbove() {
        when(mockScanner.nextLine()).thenReturn("6.0", "5.0");

        Float result = validator.readRating("5.0");

        assertEquals(5.0f, result);

        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void printMessage() {
        validator.printMessage("Test");
    }
}