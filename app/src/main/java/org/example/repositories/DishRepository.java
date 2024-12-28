package org.example.repositories;

import org.example.factory.ReviewFactory;
import org.example.models.DishFood;

import org.example.models.Review;

public class DishRepository {
    private static DishRepository instance;
    private DishFood dishFood;

    public DishRepository() {
    }

    public static DishRepository getInstance() {
        if (instance == null) {
            instance = new DishRepository();
        }
        return instance;
    }

    public void addReview(String comentario, Float... calificaciones){
        Review review = ReviewFactory.createReview("plato", comentario, calificaciones);
        dishFood.addReview(review);
    }

}
