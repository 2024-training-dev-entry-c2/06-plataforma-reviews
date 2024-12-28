package org.example.repositories;

import org.example.factory.ReviewFactory;
import org.example.models.DishFood;

import org.example.models.Review;

public class DishRepository {
    private static DishRepository instance;

    public DishRepository() {
    }

    public static synchronized DishRepository getInstance() {
        if (instance == null) {
            instance = new DishRepository();
        }
        return instance;
    }

    public void addReview(DishFood dishFood,String comentario, Float... calificaciones){
        Review review = ReviewFactory.createReview("plato", comentario, calificaciones);
        dishFood.addReview(review);
    }

}
