package org.nahulem.models;

import org.nahulem.models.interfaces.IObservable;
import org.nahulem.models.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements IObservable {
    private static Integer idCounter = 0;
    private Integer restaurantId;
    private String name;
    private String description;
    private String location;
    private Menu menu;
    private List<Review> reviews;
    private static List<IObserver> observers;

    public Restaurant(String name, String description, String location, Menu menu) {
        this.restaurantId = generateId();
        this.name = name;
        this.description = description;
        this.location = location;
        this.menu = menu;
        this.reviews = new ArrayList<>();
    }

    public Restaurant() {
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

    private Integer generateId() {
        return ++idCounter;
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
    public String toString() {
        return "\nNombre: " + name +
                "\nDescripción: " + description +
                "\nLocación : " + location +
                "\nMenú: " + menu.toString() +
                "\nReviews: " + reviews.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Restaurant.idCounter = idCounter;
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

    public static List<IObserver> getObservers() {
        return observers;
    }

    public static void setObservers(List<IObserver> observers) {
        Restaurant.observers = observers;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
