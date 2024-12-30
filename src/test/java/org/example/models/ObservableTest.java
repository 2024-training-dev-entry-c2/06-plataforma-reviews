package org.example.models;

import org.example.interfaces.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ObservableTest {
  private Observable observable;

  @BeforeEach
  void setUp() {
    observable = new Observable();
  }

  @Test
  void testAddObserverAndNotify() {
    Observer observer = mock(Observer.class);
    observable.addObserver(observer);

    observable.notifyObservers("Hello");

    verify(observer, times(1)).update("Hello");
  }
}
