package org.example.repositories;

public interface IObservable {
  void addObserver(IObserver observer);
  void notifyObservers(String typeOfReview, Long reviewedId);
}
