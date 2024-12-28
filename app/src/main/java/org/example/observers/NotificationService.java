package org.example.observers;

public class NotificationService implements IObserver {

    @Override
    public void update(String message) {
        System.out.println("Notificación: " + message);
    }
}
