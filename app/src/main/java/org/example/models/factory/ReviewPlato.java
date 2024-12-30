package org.example.models.factory;

import org.example.utils.ManejadorConsola;

import java.util.Objects;



public class ReviewPlato implements Review{

    private Double calificacion;
    private String comentario;

    public ReviewPlato(Double calificacion, String comentario) {
        this.calificacion = calificacion;
        this.comentario = comentario;
    }



    public Double getCalificacion() {
        return calificacion;
    }


    public String getComentario() {
        return comentario;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReviewPlato that = (ReviewPlato) o;
        return Objects.equals(calificacion, that.calificacion) && Objects.equals(comentario, that.comentario);
    }


}
