package org.example.reviews.repositories;

import java.util.LinkedList;
import java.util.List;

public class DishReviewRepository {

    private static DishReviewRepository INSTANCE;
    private List<DishReviewRepository> reviews;

    private DishReviewRepository(){
        this.reviews = new LinkedList<>();
    }

    public static synchronized DishReviewRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new DishReviewRepository();
        }
        return INSTANCE;
    }

    public List<DishReviewRepository> getReviews() {
        return reviews;
    }

    public void setReviews(List<DishReviewRepository> reviews) {
        this.reviews = reviews;
    }

    public void addReview(DishReviewRepository review) {
        this.reviews.add(review);
    }
}
