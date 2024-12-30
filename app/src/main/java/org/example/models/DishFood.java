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

    public String getAverageRating(){
        double suma = 0;
        for (ReviewDish review : reviewList) {
            suma += (review.getTasteRating()+review.getPresentationRating())/2;
        }
        return "Review Promedio: " + suma ;
    }

    public DishFood() {
    }
    public void addReview(IReview review){
        reviewList.add((ReviewDish) review);
    }

    public String getName() {
        return name;
    }


    public List<ReviewDish> getReviewList() {
        return reviewList;
    }


    @Override
    public String toString() {
        return "DishFood{" +
                "\nname='" + name + '\'' +
                "\ndescription='" + description + '\'' +
                "\nprice=" + price +
                "\nreview rating ="+ getAverageRating()+
                '}';
    }
}
