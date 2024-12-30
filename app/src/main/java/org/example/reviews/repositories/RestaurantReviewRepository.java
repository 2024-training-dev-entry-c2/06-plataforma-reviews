package org.example.reviews.repositories;

import org.example.reviews.models.RestaurantReview;

import java.util.LinkedList;
import java.util.List;

public class RestaurantReviewRepository {

    private static RestaurantReviewRepository INSTANCE;
    private List<RestaurantReview> reviews;

    private RestaurantReviewRepository(){
        this.reviews = new LinkedList<>();
    }

    public static synchronized RestaurantReviewRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new RestaurantReviewRepository();
        }
        return INSTANCE;
    }

    public void addReview(RestaurantReview review) {
        this.reviews.add(review);
    }

    public List<RestaurantReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RestaurantReview> reviews) {
        this.reviews = reviews;
    }


}
