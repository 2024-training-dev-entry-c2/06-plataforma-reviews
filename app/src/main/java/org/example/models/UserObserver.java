package org.example.models;

import org.example.models.interfaces.IObserver;

public class UserObserver implements IObserver {
    private final String username;

    public UserObserver(String name) {
        this.username = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Usuario: " + username + " Notificación: " + message);
    }
}
