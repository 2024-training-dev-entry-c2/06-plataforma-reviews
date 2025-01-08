package org.nahulem.models.interfaces;

public interface IObservable {

    void addObserver(IObserver observer);
    void notifyObservers(String message);
}
