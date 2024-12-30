package org.example.models;
import org.example.models.interfaces.Observable;

import java.util.LinkedList;
import java.util.List;

public class DishFood extends Observable {
    private String name;
    private String description;
    private Double price;
    private List<ReviewDish> reviewList;

    public DishFood(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviewList = new LinkedList<>();
    }

    public String getAverageRating(){
        double suma = 0;
        for (ReviewDish review : reviewList) {
            double promedio = (review.getTasteRating() + review.getPresentationRating()) / 2.0;
            suma += promedio;
        }
        return "Review Promedio: " + (suma / reviewList.size());
    }

    public DishFood() {
    }
    public void addReview(IReview review){
        reviewList.add((ReviewDish) review);
        notifyObservers("se ha agregado un nuevo review sobre "+name);
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
