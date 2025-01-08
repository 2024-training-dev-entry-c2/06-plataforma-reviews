package org.nahulem.models;

import org.nahulem.models.interfaces.IObservable;
import org.nahulem.models.interfaces.IObserver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dish implements IObservable {
    private static Integer idCounter = 0;
    private Integer dishId;
    private String name;
    private String description;
    private Float price;
    private List<Review> reviews;
    private final Set<IObserver> observerSet = new HashSet<>();

    public Dish(String name, String description, Float price) {
        this.dishId = generateId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = new LinkedList<>();
    }

    @Override
    public void addObserver(IObserver observer) {
        observerSet.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observerSet.forEach(observer -> observer.update(message));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Nombre: ");
        result.append(name).append("\n")
                .append("Descripción: ").append(description).append("\n")
                .append("Precio: $").append(price).append("\n")
                .append("Reseñas del plato ").append(name).append(" :\n");

        if (reviews.isEmpty()) {
            result.append(" No hay reseñas.");
        } else {
            for (Review review : reviews) {
                result.append("    - ").append(review.toString()).append("\n");
            }
            result.append("\n____________________________________________________");
        }

        return result.toString();
    }

    private Integer generateId() {
        return ++idCounter;
    }

    public void addReview(Review review) {
        reviews.add(review);
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

}