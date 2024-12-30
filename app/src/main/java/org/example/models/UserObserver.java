package org.example.models;

import org.example.models.interfaces.IObserver;

public class UserObserver implements IObserver {
	String userName;

	public UserObserver(String userName) {
		this.userName = userName;
	}

	@Override
	public void update(String message) {
		System.out.printf("""
			======================================
			Notificación para: %s
			--------------------------------------
			%s
			======================================
			%n""", userName, message);
	}
}