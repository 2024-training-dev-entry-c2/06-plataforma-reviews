package org.example.models;

import java.util.ArrayList;
import java.util.Objects;

public class Menu {
    private ArrayList<Plato> platos;
    private String nombre;



    public Menu(String nombre) {
        this.nombre = nombre;
        this.platos = new ArrayList<>();
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }


    public String getNombre() {
        return nombre;
    }

}
