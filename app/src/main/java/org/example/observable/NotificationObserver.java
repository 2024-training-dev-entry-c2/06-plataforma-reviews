package org.example.observable;

import org.example.interfaces.IObserver;

public class NotificationObserver implements IObserver {
  @Override
  public void update(String message) {
    System.out.println(message);
  }
}
