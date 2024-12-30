package org.example.services;

import org.example.models.Menu;
import org.example.models.Observer.Observer;
import org.example.models.Restaurante;
import org.example.models.factory.ReviewRestaurante;
import org.example.repositories.RestaranteRepository;

public class RestauranteService {

    private RestaranteRepository restaranteRepository;

    public RestauranteService() {
        this.restaranteRepository = RestaranteRepository.getInstance();
    }

    public void guardarRestaurante(Restaurante restaurante){
       this.restaranteRepository.guardarRestaurante(restaurante);
    }

    public Boolean elimiarRestaurante(Restaurante restaurante){
       return this.restaranteRepository.elimiarRestaurante(restaurante);
    }

    public Boolean actualizarRestaurante(Restaurante restaurante){
        return this.restaranteRepository.actualizarRestaurante(restaurante);
    }

    public void mostrarInfo(){
        this.restaranteRepository.mostrarInfo();
    }


    public void agregarReviewRestaurante(String nombreRestaurante, ReviewRestaurante review) {
        Restaurante restaurante = restaranteRepository.buscarRestaurante(nombreRestaurante);
        if (restaurante != null) {
            restaurante.addReview(review);
            restaranteRepository.actualizarRestaurante(restaurante);
            System.out.println("Reseña agregada con éxito al restaurante.");
        } else {
            System.out.println("No se encontró el restaurante.");
        }
    }

    public void listarReviewsRestaurante(String nombreRestaurante) {
        Restaurante restaurante = restaranteRepository.buscarRestaurante(nombreRestaurante);
        if (restaurante != null) {
            restaurante.getReviews().forEach(review -> {
                System.out.println("Calificación: " + review.getCalificacion());
                System.out.println("Comentario: " + review.getComentario());
                System.out.println("-----------------------------------");
            });
        } else {
            System.out.println("No se encontró el restaurante.");
        }
    }

    public Boolean registrarSuscriptor(String nombreRestaurante, Observer suscriptor){
       return this.restaranteRepository.registrarSuscriptor(nombreRestaurante,suscriptor);
    }

    public Boolean eliminarSuscriptor(String nombreRestaurante,Observer suscriptor){
        return this.restaranteRepository.eliminarSuscriptor(nombreRestaurante,suscriptor);
    }


    public Restaurante buscarRestaurante(String nombreRestaurante) {
        return restaranteRepository.buscarRestaurante(nombreRestaurante);
    }



}
