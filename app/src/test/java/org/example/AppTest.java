package org.example;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

	@Test
	void main() {
		assertThrows(NoSuchElementException.class, () -> App.main(null));
	}
}