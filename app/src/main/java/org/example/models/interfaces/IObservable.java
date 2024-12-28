package org.example.models.interfaces;

public interface IObservable {

    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(String message);
}
