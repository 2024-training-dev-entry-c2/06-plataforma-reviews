package org.example.repositories;

import org.example.factory.ReviewFactory;
import org.example.models.*;

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
        IReview review = ReviewFactory.createReview("plato", comentario, calificaciones);
        dishFood.addReview(review);
    }
    public void showReview(DishFood dishFood){
        dishFood.getReviewList().forEach(reviewDish -> {
            System.out.println(
            " - Calificación presentacion : " + reviewDish.getPresentationRating() +"\n " +
                    "Califacion sabor :" + reviewDish.getTasteRating() +"\n");
            System.out.println("Comentario: " + reviewDish.getComment());
            System.out.println("----------------------------");

        });
    }



}
