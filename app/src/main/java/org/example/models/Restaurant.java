package org.example.models;

import org.example.models.interfaces.IObservable;
import org.example.models.interfaces.IObserver;

import java.util.List;

public class Restaurant implements IObservable {
    private static Integer restaurantCounter = 0;
    private Integer restaurantId;
    private String name;
    private String location;
    private Menu menu;
    private List<Review> reviews;
    private static List<IObserver> observers;

    public Restaurant(Integer restaurantId, String name, String location, Menu menu, List<Review> reviews) {
        this.restaurantId = generateId();
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.reviews = reviews;
    }

    public Restaurant() {
    }

    private Integer generateId() {
        return ++restaurantCounter;
    }

    public void addMenu(Menu menu) {
        this.menu = menu;
    }

    public void addReview(Review review) {
        reviews.add(review);
        notifyObservers("Nueva reseña añadida al restaurante: " + name);
    }

    public float calculateAverageRating() {
        float average = (float) reviews.stream().mapToDouble(Review::getAverageRating).average().orElse(0.0);
        notifyObservers("Calificación promedio actualizada para el restaurante: " + name + " - Promedio: " + average);
        return average;
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
       observers.forEach(observer -> observer.update(message));
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
