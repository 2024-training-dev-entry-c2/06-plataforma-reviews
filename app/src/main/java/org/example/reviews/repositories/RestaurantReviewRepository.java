package org.example.reviews.repositories;

import java.util.LinkedList;
import java.util.List;

public class RestaurantReviewRepository {

    private static RestaurantReviewRepository INSTANCE;
    private List<RestaurantReviewRepository> reviews;

    private RestaurantReviewRepository(){
        this.reviews = new LinkedList<>();
    }

    public static synchronized RestaurantReviewRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new RestaurantReviewRepository();
        }
        return INSTANCE;
    }

    public void addReview(RestaurantReviewRepository review) {
        this.reviews.add(review);
    }

    public List<RestaurantReviewRepository> getReviews() {
        return reviews;
    }

    public void setReviews(List<RestaurantReviewRepository> reviews) {
        this.reviews = reviews;
    }


}
