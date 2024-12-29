package org.example.utils;

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
	void printMessage() {
		validator.printMessage("Test");
	}

	@Test
	void readInteger() {
		when(mockScanner.nextLine()).thenReturn("1");

		Integer result = validator.readInteger("1");

		assertEquals(1, result);

		verify(mockScanner).nextLine();
	}

	@Test
	void readWrongInteger() {
		when(mockScanner.nextLine()).thenReturn("a", "1");

		Integer result = validator.readInteger("1");

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
		when(mockScanner.nextLine()).thenReturn("a","1.0");

		Float result = validator.readFloat("1.0");

		assertEquals(1.0f, result);

		verify(mockScanner, times(2)).nextLine();
	}

	@Test
	void readRating() {
		when(mockScanner.nextLine()).thenReturn("4.0");

		Float result = validator.readRating("4.0");

		assertEquals(4.0f, result);

		verify(mockScanner).nextLine();
	}

	@Test
	void readWrongRatingBelowRange() {
		when(mockScanner.nextLine()).thenReturn("-1.0", "4.0");

		Float result = validator.readRating("4.0");

		assertEquals(4.0f, result);

		verify(mockScanner, times(2)).nextLine();
	}

	@Test
	void readWrongRatingAboveRange() {
		when(mockScanner.nextLine()).thenReturn("6.0", "4.0");

		Float result = validator.readRating("4.0");

		assertEquals(4.0f, result);

		verify(mockScanner, times(2)).nextLine();
	}
}