package restaurant.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IObserverTest {
    @Test
    public void testUpdate() {
        IObserver observer = new ReviewNotifier();
        assertDoesNotThrow(() -> observer.update("Test message"));
    }
}