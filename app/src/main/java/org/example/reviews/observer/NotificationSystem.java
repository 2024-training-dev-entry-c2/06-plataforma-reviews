package org.example.reviews.observer;

public class NotificationSystem implements Observer {
    @Override
    public void update(String message) {
        System.out.println("*** Notificaciones del sistema: " + message);
    }
}
