package org.example.repositories;

import org.example.models.Observer.Observer;
import org.example.models.Restaurante;

import java.util.HashMap;
import java.util.Map;

public class RestaranteRepository {
    private static RestaranteRepository instance;
    private Map<String, Restaurante> restaurantes;

    private RestaranteRepository() {
        this.restaurantes = new HashMap<>();
    }

    public static synchronized RestaranteRepository getInstance(){
        if (instance==null){
            instance = new RestaranteRepository();
        }
        return instance;
    }

    public void guardarRestaurante(Restaurante restaurante){
        restaurantes.put(restaurante.getNombre(),restaurante);
    }

    public Boolean elimiarRestaurante(Restaurante restaurante){
        if(restaurantes.containsKey(restaurante.getNombre()) && restaurante!=null){
            restaurantes.remove(restaurante.getNombre());
            return true;
        }
        return false;
    }

    public Boolean actualizarRestaurante(Restaurante restaurante){
        if(restaurantes.containsKey(restaurante.getNombre())){
            restaurantes.put(restaurante.getNombre(),restaurante);
            return true;
        }
        return false;
    }

    public void mostrarInfo(){
        restaurantes.forEach((clave, restaurante) -> {
            System.out.println("Nombre: " + restaurante.getNombre());
            System.out.println("Dirección: " + restaurante.getDireccion());
            System.out.println("Horario: " + restaurante.getHorario());
            System.out.println("Calificación: " + restaurante.getCalificacion());
            System.out.println("Descripción: " + restaurante.getDescripcion());
            System.out.println("-----------------------------------");
        });
    }

    public Boolean registrarSuscriptor(String nombreRestaurante,Observer suscriptor){
        if(restaurantes.containsKey(nombreRestaurante)){
            Restaurante restaurante = buscarRestaurante(nombreRestaurante);
            restaurante.suscribirse(suscriptor);
            return true;
        }else{
            return false;
        }
    }

    public Boolean eliminarSuscriptor(String nombreRestaurante,Observer suscriptor){
        if(restaurantes.containsKey(nombreRestaurante)){
            Restaurante restaurante = buscarRestaurante(nombreRestaurante);
            restaurante.desuscribirse(suscriptor);
            return true;
        }else{
            return false;
        }
    }


    public Restaurante buscarRestaurante(String nombreRestaurante) {
        return restaurantes.get(nombreRestaurante);
    }


}
