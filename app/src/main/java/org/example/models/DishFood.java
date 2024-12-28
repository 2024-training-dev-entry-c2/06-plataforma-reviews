package org.example.models;

import java.util.List;

public class DishFood {
    private String name;
    private String description;
    private Double price;
    private List<Review> reviewList;

    public DishFood(String name, String description, Double price, List<Review> reviewList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviewList = reviewList;
    }

    public DishFood() {
    }
    public void addReview(Review review){
        reviewList.add(review);
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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "DishFood{" +
                "\nname='" + name + '\'' +
                "\ndescription='" + description + '\'' +
                "\nprice=" + price +
                '}';
    }
}
