package org.example.models;

import org.example.observers.Observable;

import java.util.ArrayList;
import java.util.List;

public class Dish extends Observable {
    private String dishId;
    private String name;
    private String description;
    private Float price;
    private List<Review> reviews;

    public Dish(String dishId, String name, String description, Float price, List<Review> reviews) {
        this.dishId = dishId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
        notifyObservers("Nueva reseña añadida al plato: " + name);
    }

    public float calculateAverageRating() {
        float average = (float) reviews.stream().mapToDouble(Review::getAverageRating).average().orElse(0.0);
        notifyObservers("Calificación promedio actualizada para el plato: " + name + " - Promedio: " + average);
        return average;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}