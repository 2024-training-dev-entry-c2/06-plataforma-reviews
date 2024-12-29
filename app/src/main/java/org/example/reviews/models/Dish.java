package org.example.reviews.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class Dish {
    private Integer id;
    private String name;
    private Double price;
    private List<DishReview> reviews;

    public Dish(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public Dish() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<DishReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<DishReview> reviews) {
        this.reviews = reviews;
    }
}
