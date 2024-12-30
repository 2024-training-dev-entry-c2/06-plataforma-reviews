package org.example.models.factory;

public class ReviewPlatoFactory extends Factory{
    @Override
    public Review crearReview(Double calificacion, String comentario) {
        return new ReviewPlato(calificacion,comentario);
    }

}
