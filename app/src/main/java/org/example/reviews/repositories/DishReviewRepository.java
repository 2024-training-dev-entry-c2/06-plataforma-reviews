package org.example.reviews.repositories;

import org.example.reviews.models.DishReview;

import java.util.LinkedList;
import java.util.List;

public class DishReviewRepository {

    private static DishReviewRepository INSTANCE;
    private List<DishReview> reviews;

    private DishReviewRepository(){
        this.reviews = new LinkedList<>();
    }

    public static synchronized DishReviewRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new DishReviewRepository();
        }
        return INSTANCE;
    }

    public List<DishReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<DishReview> reviews) {
        this.reviews = reviews;
    }

    public void addReview(DishReview review) {
        this.reviews.add(review);
    }
}
