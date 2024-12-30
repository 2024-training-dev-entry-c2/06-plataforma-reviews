// src/main/java/restaurant/observer/ReviewNotifier.java
package restaurant.observer;

public class ReviewNotifier implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("Notification del Observer: " + message);
    }
}