package org.nahulem.models;

import org.nahulem.models.interfaces.IObservable;
import org.nahulem.models.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Observable implements IObservable {
    protected final List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.update(message));
    }
}
