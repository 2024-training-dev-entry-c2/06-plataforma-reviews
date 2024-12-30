package org.example.services;

import org.example.models.Menu;
import org.example.models.Plato;
import org.example.models.factory.ReviewPlato;
import org.example.repositories.MenuRepository;

import java.util.ArrayList;

public class MenuService {

    private MenuRepository menuRepository;

    public MenuService() {
        this.menuRepository = MenuRepository.getInstance();
    }

    public void guardarMenu(String nombreRestaurante, Menu menu){
        this.menuRepository.guardarMenu(nombreRestaurante,menu);
    }

    public Boolean eliminarMenu(String nombreRestaurante, Menu menu){
        return this.menuRepository.eliminarMenu(nombreRestaurante,menu);
    }

    public Boolean agregarPlatoAUnMenu(String nombreRestaurante, Plato plato) {
      return this.menuRepository.agregarPlatoAUnMenu(nombreRestaurante,plato);
    }

    public Boolean editarPlatoEnMenu(String nombreRestaurante, Plato plato) {
      return this.menuRepository.editarPlatoEnMenu(nombreRestaurante,plato);
    }

    public Boolean eliminarPlatoDeMenu(String nombreRestaurante, String nombrePlato) {
        return this.menuRepository.eliminarPlatoDeMenu(nombreRestaurante,nombrePlato);
    }

    public void mostrarMenu(String nombreRestaurante) {
        this.menuRepository.mostrarMenu(nombreRestaurante);
    }

    public void agregarReviewPlato(String nombreRestaurante, String nombrePlato, ReviewPlato review) {
        Menu menu = menuRepository.getMenu(nombreRestaurante);
        if (menu != null) {
            Plato plato = menu.getPlatos().stream()
                    .filter(p -> p.getNombre().equals(nombrePlato))
                    .findFirst()
                    .orElse(null);
            if (plato != null) {
                plato.addReview(review);
                System.out.println("Reseña agregada con éxito al plato.");
            } else {
                System.out.println("No se encontró el plato.");
            }
        } else {
            System.out.println("No se encontró el menú para el restaurante.");
        }
    }

    public void listarReviewsPlato(String nombreRestaurante, String nombrePlato) {
        Menu menu = menuRepository.getMenu(nombreRestaurante);
        if (menu != null) {
            Plato plato = menu.getPlatos().stream()
                    .filter(p -> p.getNombre().equals(nombrePlato))
                    .findFirst()
                    .orElse(null);
            if (plato != null) {
                plato.getReviews().forEach(review -> {
                    System.out.println("Calificación: " + review.getCalificacion());
                    System.out.println("Comentario: " + review.getComentario());
                    System.out.println("-----------------------------------");
                });
            } else {
                System.out.println("No se encontró el plato.");
            }
        } else {
            System.out.println("No se encontró el menú para el restaurante.");
        }
    }

    public Menu getMenu(String nombreRestaurante) {
        return this.menuRepository.getMenu(nombreRestaurante);
    }




}
