package org.example.observer;

public class ConsoleNotifier implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Notificación: " + message);
    }
}