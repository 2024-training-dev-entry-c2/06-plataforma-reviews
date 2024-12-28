package org.example;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.models.UserObserver;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Dish empanadas = new Dish("1", "Empanadas de carne", "Empanadas rellenas de carne cortada a cuchillo.", 3.5f, new ArrayList<>());
        Restaurant parrilla = new Restaurant("1", "Parrilla Don Asado", "Av. Siempre Viva 742", new ArrayList<>(), new ArrayList<>());

        UserObserver notificationService = new UserObserver("Notificaciones");

        empanadas.addObserver(notificationService);
        parrilla.addObserver(notificationService);

        empanadas.addReview(new DishReview("101", "Muy ricas, excelente calidad.", 5.0f, 4.5f));
        empanadas.calculateAverageRating();

        parrilla.addReview(new RestaurantReview("201", "El mejor lugar para un asado argentino.", 5.0f, 4.7f, 4.8f));
        parrilla.calculateAverageRating();
    }
}
