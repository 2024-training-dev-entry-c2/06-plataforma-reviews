package org.nahulem.models;

import org.nahulem.models.interfaces.IObservable;
import org.nahulem.models.interfaces.IObserver;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Restaurant implements IObservable {
    private static Integer idCounter = 0;
    private Integer restaurantId;
    private String name;
    private String description;
    private String location;
    private Menu menu;
    private List<Review> reviews;
    private final Set<IObserver> observerSet = new HashSet<>();

    public Restaurant(String name, String description, String location, Menu menu) {
        this.restaurantId = generateId();
        this.name = name;
        this.description = description;
        this.location = location;
        this.menu = menu;
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

    private Integer generateId() {
        return ++idCounter;
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
        StringBuilder result = new StringBuilder("===============================================\n");
        result.append("Nombre: ").append(name).append("\n")
                .append("Descripción: ").append(description).append("\n")
                .append("Locación: ").append(location).append("\n")
                .append("Calificacion: ").append(calculateAverageRating()).append("\n")
                .append("Menú: ").append(menu.getName()).append("\n\n")
                .append("Platos:\n");

        for (Dish dish : menu.getDishes()) {
            result.append(dish.toString()).append("\n");
        }

        result.append("\nReseñas del restaurante:\n");
        if (reviews.isEmpty()) {
            result.append("  No hay reseñas.\n");
        } else {
            for (Review review : reviews) {
                result.append("  - ").append(review.toString()).append("\n");
            }
        }

        return result.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}