package org.example.models;

import java.util.List;

public class Dish {
    private String name;
    private String description;
    private Double price;
    private List<Review> reviews;

    public Dish() {
    }

    public Dish(String name, String description, Double price, List<Review> reviews) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}