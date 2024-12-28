package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String id;
    private String name;
    private String description;
    private Float price;
    private List<Review> reviews;

    public Dish(String id, String name, String description, Float price, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public float calculateAverageRating() {
        return (float) reviews.stream().mapToDouble(Review::getAverageRating).average().orElse(0.0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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