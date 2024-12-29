package org.example.factory;

import org.example.models.IReview;

import org.example.models.ReviewDish;
import org.example.models.ReviewRestaurant;

public class ReviewFactory {
    public static IReview createReview(String type, String comentario, Float... calificaciones) {
        if ("restaurante".equalsIgnoreCase(type)) {
            if (calificaciones.length < 3) {
                throw new IllegalArgumentException("Se requieren 3 calificaciones para una reseña de restaurante.");
            }
            return new ReviewRestaurant(comentario, calificaciones[0], calificaciones[1], calificaciones[2]);
        } else if ("plato".equalsIgnoreCase(type)) {
            if (calificaciones.length < 2) {
                throw new IllegalArgumentException("Se requieren 2 calificaciones para una reseña de plato.");
            }
            return new ReviewDish(comentario, calificaciones[0], calificaciones[1]);
        }
        throw new IllegalArgumentException("Tipo de reseña no válido");
    }
}
