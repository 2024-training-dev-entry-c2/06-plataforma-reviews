package org.example.observers;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<IObserver> observers = new ArrayList<>();

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }
}
