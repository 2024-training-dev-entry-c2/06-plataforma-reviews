package org.example.repositories;

public class ReviewNotifier implements IObserver {
  @Override
  public void update(String message) {
    System.out.println(message);
  }
}
