package org.nahulem.models;

import org.nahulem.models.interfaces.IObserver;

public class UserObserver implements IObserver {
    private final String username;
    private String lastMessage;

    public UserObserver(String name) {
        this.username = name;
    }

    @Override
    public void update(String message) {
        lastMessage = message;
        System.out.println("Usuario: " + username + " Notificación: " + message);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
