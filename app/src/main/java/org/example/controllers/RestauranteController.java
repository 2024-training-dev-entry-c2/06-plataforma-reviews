package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Observer.Observer;
import org.example.models.Restaurante;
import org.example.models.factory.ReviewRestaurante;
import org.example.repositories.RestaranteRepository;
import org.example.services.RestauranteService;

public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController() {
        this.restauranteService = new RestauranteService();
    }

    public void guardarRestaurante(Restaurante restaurante){
        this.restauranteService.guardarRestaurante(restaurante);
        System.out.println("Restaurante agregado con exito");
    }

    public void elimiarRestaurante(String nombreRestaurante){
        Restaurante restaurante = restauranteService.buscarRestaurante(nombreRestaurante);
        if(this.restauranteService.elimiarRestaurante(restaurante)){
             System.out.println("Restaurante eliminado con exito");
         }else{
             System.out.println("No fue posible eliminar el restaurante, porfavor validar");
         }
    }

    public void actualizarRestaurante(Restaurante restaurante){
        //Restaurante restaurante = restauranteService.buscarRestaurante(nombreRestaurante);
        if(this.restauranteService.actualizarRestaurante(restaurante)){
           System.out.println("Restaurante actualizado con exito");
       }else{
           System.out.println("No fue posible actualizar el restaurante, porfavor validar");
       }
    }

    public void mostrarInfo(){
        this.restauranteService.mostrarInfo();
    }


    public void agregarReviewRestaurante(String nombreRestaurante, ReviewRestaurante review) {
        restauranteService.agregarReviewRestaurante(nombreRestaurante, review);
    }

    public void listarReviewsRestaurante(String nombreRestaurante) {
        restauranteService.listarReviewsRestaurante(nombreRestaurante);
    }

    public void registrarSuscriptor(String nombreRestaurante, Observer suscriptor){
         if(this.restauranteService.registrarSuscriptor(nombreRestaurante,suscriptor)){
             System.out.println("Suscripcion exitosa");
         }
    }

    public void eliminarSuscriptor(String nombreRestaurante,Observer suscriptor){
        if(this.restauranteService.eliminarSuscriptor(nombreRestaurante,suscriptor)){
            System.out.println("Desuscripcion exitosa");
        }
    }


}
