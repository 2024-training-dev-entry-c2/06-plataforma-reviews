package org.example.models;

import org.example.models.Observer.Observer;

public class Suscriptor implements Observer{

    private String nombre;

    public Suscriptor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar() {
        System.out.println("Hola " + nombre +" El restaurante ha agregado un nuevo plato!, echale un vistazo");
    }


}
