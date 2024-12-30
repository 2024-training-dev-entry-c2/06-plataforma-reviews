package org.example.models;

import org.example.models.factory.ReviewPlato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Plato {

    private String nombre;
    private Set<String> ingredientes;
    private String descripcion;
    private Double calificacion;
    private Double precio;
    private List<ReviewPlato> reviews;

    public Plato(String nombre, Set<String> ingredientes, String descripcion, Double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.calificacion = 0.0;
        this.precio = precio;
        this.reviews = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }


    public Set<String> getIngredientes() {
        return ingredientes;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public List<ReviewPlato> getReviews() {
        return reviews;
    }


    public void addReview(ReviewPlato review) {
        this.reviews.add(review);
        this.setCalificacion(calcularPromedioCalificaciones());
    }

    public double calcularPromedioCalificaciones() {
        return reviews.stream()
                .mapToDouble(ReviewPlato::getCalificacion)
                .average()
                .orElse(0.0);
    }

}
