package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Plato;
import org.example.models.Restaurante;
import org.example.models.factory.ReviewPlato;
import org.example.services.MenuService;

public class MenuController {

    private MenuService menuService;

    public MenuController() {
        this.menuService = new MenuService();
    }


    public void guardarMenu(String nombreRestaurante, Menu menu){
        this.menuService.guardarMenu(nombreRestaurante,menu);
    }

    public void eliminarMenu(String nombreRestaurante, Menu menu){

        if(this.menuService.eliminarMenu(nombreRestaurante,menu)){
            System.out.println("Menu eliminado con exito");
        }else{
            System.out.println("Error al eliminar el menu");
        }
    }

    public void agregarPlatoAUnMenu(String nombreRestaurante, Plato plato) {
        if(this.menuService.agregarPlatoAUnMenu(nombreRestaurante,plato)){
            System.out.println("Plato agregado con exito");
        }else{
            System.out.println("Error al agregar el plato");
        }
    }

    public void editarPlatoEnMenu(String nombreRestaurante, Plato plato) {
        if(this.menuService.editarPlatoEnMenu(nombreRestaurante,plato)){
            System.out.println("Plato editado con exito");
        }else{
            System.out.println("Error al editar el plato");
        }
    }

    public void eliminarPlatoDeMenu(String nombreRestaurante, String nombrePlato) {
        if(this.menuService.eliminarPlatoDeMenu(nombreRestaurante,nombrePlato)){
            System.out.println("Plato eliminado con exito");
        }else{
            System.out.println("Error al elimiar el plato");
        }
    }

    public void mostrarMenu(String nombreRestaurante) {
        this.menuService.mostrarMenu(nombreRestaurante);
    }

    public void agregarReviewPlato(String nombreRestaurante, String nombrePlato, ReviewPlato review) {
        menuService.agregarReviewPlato(nombreRestaurante, nombrePlato, review);
    }

    public void listarReviewsPlato(String nombreRestaurante, String nombrePlato) {
        menuService.listarReviewsPlato(nombreRestaurante, nombrePlato);
    }

    public Menu getMenu(String nombreRestaurante) {
        return this.menuService.getMenu(nombreRestaurante);
    }


}
