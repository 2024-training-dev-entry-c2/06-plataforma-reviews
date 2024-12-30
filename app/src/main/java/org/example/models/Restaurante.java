package org.example.models;

import org.example.models.Observer.Eventos;
import org.example.models.Observer.Observer;
import org.example.models.factory.Review;
import org.example.models.factory.ReviewRestaurante;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Restaurante implements Eventos {

    private String nombre;
    private String direccion;
    private String horario;
    private Double calificacion;
    private String descripcion;
    private Menu menu;
    private LinkedList<Review> reviews;
    private ArrayList<Observer> suscriptores;

    public Restaurante(String nombre, String direccion, String horario, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;
        this.calificacion = 0.0;
        this.descripcion = descripcion;
        this.reviews = new LinkedList<>();
        this.suscriptores = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }


    public String getDireccion() {
        return direccion;
    }


    public String getHorario() {
        return horario;
    }


    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public LinkedList<Review> getReviews() {
        return reviews;
    }


    public void addReview(ReviewRestaurante review) {
        this.reviews.add(review);
        this.setCalificacion(calcularPromedioCalificaciones());
    }

    public double calcularPromedioCalificaciones() {
        return reviews.stream()
                .filter(review -> review instanceof ReviewRestaurante)
                .mapToDouble(review -> ((ReviewRestaurante) review).getCalificacion())
                .average()
                .orElse(0.0);
    }

    public ArrayList<Observer> getSuscriptores() {
        return suscriptores;
    }

    @Override
    public void suscribirse(Observer observer) {
        suscriptores.add(observer);
    }

    @Override
    public void desuscribirse(Observer observer) {
        suscriptores.remove(observer);
    }

    @Override
    public void notificar() {
        for (Observer suscriptor : suscriptores) {
            suscriptor.actualizar();
        }
    }


}
