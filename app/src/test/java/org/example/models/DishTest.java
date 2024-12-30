package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {

	@Test
	void addObserver() {
		Dish dish = new Dish();

		UserObserver userObserver = new UserObserver("Usuario Test");
		dish.addObserver(userObserver);
	}
}