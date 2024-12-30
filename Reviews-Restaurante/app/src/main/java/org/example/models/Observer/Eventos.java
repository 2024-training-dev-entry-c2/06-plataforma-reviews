package org.example.models.Observer;

public interface Eventos {

    void suscribirse(Observer observer);
    void desuscribirse (Observer observer);
    void notificar();

}
