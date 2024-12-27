package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String location;
    private Menu menu;
    private List<Review> reviews;

    public Restaurant(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.reviews = new ArrayList<>();
    }

    public void addMenu(Menu menu) {
        this.menu = menu;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public float calculateAverageRating() {
        return (float) reviews.stream().mapToDouble(Review::getAverageRating).average().orElse(0.0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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