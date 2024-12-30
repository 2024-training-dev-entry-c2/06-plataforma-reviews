package org.example.models.factory;

public class ReviewRestauranteFactory extends Factory{

    @Override
    public Review crearReview(Double calificacion, String comentario) {
        return new ReviewRestaurante(calificacion,comentario);
    }

}
