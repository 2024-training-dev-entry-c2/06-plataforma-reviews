package org.nahulem.models;

import org.nahulem.models.interfaces.IObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dish extends Observable {
    private static Integer idCounter = 0;
    private Integer dishId;
    private String name;
    private String description;
    private Float price;
    private List<Review> reviews;
    private final LinkedList<IObserver> observers = new LinkedList<>();


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
    }

    @Override
    public String toString() {
        String result = "Nombre: " + name +
                "\nDescripción: " + description +
                "\nPrecio: $" + price +
                "\nReseñas del plato " + name + " :";

        if (reviews.isEmpty()) {
            result += " No hay reseñas.";
        } else {
            for (Review review : reviews) {
                result += "\n    - " + review.toString();
            }
            result += "\n____________________________________________________";
        }

        return result;
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


    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.update(message));
    }
}