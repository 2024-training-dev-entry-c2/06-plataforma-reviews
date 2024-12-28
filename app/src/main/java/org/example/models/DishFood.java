package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class DishFood {
    private String name;
    private String description;
    private Double price;
    private List<ReviewDish> reviewList;

    public DishFood(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviewList = new ArrayList<>();
    }

    public void getAverageRating(){
        double suma = 0;
        for (ReviewDish review : reviewList) {
            suma += (review.getTasteRating()+review.getPresentationRating())/2;
        }
        System.out.println("Review Promedio: " + suma +" :3");
    }

    public DishFood() {
    }
    public void addReview(IReview review){
        reviewList.add((ReviewDish) review);
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

    public List<ReviewDish> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDish> reviewList) {
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
