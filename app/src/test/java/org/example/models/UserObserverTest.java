package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserObserverTest {

	@Test
	void update() {
		UserObserver userObserver = new UserObserver("Usuario Test");
		userObserver.update("Mensaje Test");
	}
}