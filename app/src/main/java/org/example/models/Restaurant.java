package org.example.models;

import org.example.observers.Observable;

import java.util.List;

public class Restaurant extends Observable {
    private String restaurantId;
    private String name;
    private String location;
    private Menu menu;
    private List<Review> reviews;

    public Restaurant(String restaurantId, String name, String location, Menu menu, List<Review> reviews) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.reviews = reviews;
    }

    public Restaurant() {
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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