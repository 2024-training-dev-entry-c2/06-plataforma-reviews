package org.example.factory;

import org.example.models.IReview;

import org.example.models.ReviewDish;
import org.example.models.ReviewRestaurant;

public class ReviewFactory {
    public static IReview createReview(String type, String comentario, Float... calificaciones) {
        if ("restaurante".equalsIgnoreCase(type)) {
            return new ReviewRestaurant(comentario,calificaciones[0],calificaciones[1],calificaciones[2]);
        } else if ("plato".equalsIgnoreCase(type)) {
            return new ReviewDish(comentario, calificaciones[0], calificaciones[1]);
        }
        throw new IllegalArgumentException("Tipo de review no válido");
    }
}
