package org.example.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

	@Test
	void setDescription() {
		Menu menu = new Menu();
		menu.setDescription("Descripcion Test");
	}

	@Test
	void testToString() {
		Menu menu = new Menu("Menu Test", "Descripcion Test", new HashSet<>());
		String result = menu.toString();
	}
}