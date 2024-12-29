package org.nahulem.models;

import org.nahulem.models.interfaces.IObservable;
import org.nahulem.models.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Dish implements IObservable {
    private static Integer idCounter = 0;
    private Integer dishId;
    private String name;
    private String description;
    private Float price;
    private List<Review> reviews;
    private static List<IObserver> observers;


    public Dish(String name, String description, Float price, List<Review> reviews) {
        this.dishId = generateId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public Dish() {
    }

    private Integer generateId() {
        return ++idCounter;
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

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
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
}