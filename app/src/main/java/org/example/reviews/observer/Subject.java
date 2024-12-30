package org.example.reviews.observer;


public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
    Boolean hasObserver(Observer observer);
}
