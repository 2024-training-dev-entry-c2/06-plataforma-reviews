package org.example.repositories;

import org.example.factories.ReviewFactory;
import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.models.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepository {
    private static DataRepository instance;
    private Map<Integer, Restaurant> restaurants;

    private DataRepository() {
        this.restaurants = new HashMap<>();
        initialize();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public void addRestaurant(Integer id, Restaurant restaurant) {
        restaurants.put(id, restaurant);
    }

    public Restaurant getRestaurant(Integer id) {
        return restaurants.get(id);
    }

    public Collection<Restaurant> getAllRestaurants() {
        return restaurants.values();
    }

    public void deleteRestaurant(Integer id) {
        restaurants.remove(id);
    }


    private void initialize() {
        List<Review> parrillaDish1Reviews = new ArrayList<>();
        parrillaDish1Reviews.add(ReviewFactory.createReview("Dish", "1", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f, 5.0f));
        Dish parrillaDish1 = new Dish("1", "Empanadas de carne", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f, parrillaDish1Reviews);


        List<Review> parrillaDish2Reviews = new ArrayList<>();
        parrillaDish2Reviews.add(ReviewFactory.createReview("Dish", "2", "Corte clásico argentino cocido a la parrilla.", 4.0f, 4.5f));
        Dish parrillaDish2 = new Dish("2", "Asado de tira", "Corte clásico argentino cocido a la parrilla.", 15.0f, parrillaDish2Reviews);

        List<Review> parrillaDish3Reviews = new ArrayList<>();
        parrillaDish3Reviews.add(ReviewFactory.createReview("Dish", "3", "Flan casero acompañado de dulce de leche artesanal.", 5.0f, 4.5f));
        Dish parrillaDish3 = new Dish("3", "Flan con dulce de leche", "Flan casero acompañado de dulce de leche artesanal.", 5.0f, parrillaDish3Reviews);

        Menu parrillaMenu = new Menu("1", List.of(parrillaDish1, parrillaDish2, parrillaDish3));

        List<Review> parrillaReviews = new ArrayList<>();
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "1","El mejor asado que he probado en mi vida.", 5.0f, 4.3f, 5.0f));
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "2", "La atención fue excelente, muy recomendado.", 4.5f, 4.0f, 4.5f));
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "3", "La parrilla es un clásico de Buenos Aires, muy buena.", 4.0f, 4.0f, 4.0f));

        Restaurant parrilla = new Restaurant(1, "La Parrilla Porteña", "San Telmo, Buenos Aires", parrillaMenu, parrillaReviews);

        // Crear platos para "Casa de Pastas Don Pepe"
        List<Review> pastasDish1Reviews = new ArrayList<>();
        pastasDish1Reviews.add(ReviewFactory.createReview("Dish", "4", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 4.5f, 4.0f));
        Dish pastasDish1 = new Dish("4", "Ravioles de ricota y espinaca", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 10.0f, pastasDish1Reviews);

        List<Review> pastasDish2Reviews = new ArrayList<>();
        pastasDish2Reviews.add(ReviewFactory.createReview("Dish", "5", "Fideos caseros con salsa bolognesa y queso rallado.", 4.0f, 4.0f));
        Dish pastasDish2 = new Dish("5", "Fideos a la bolognesa", "Fideos caseros con salsa bolognesa y queso rallado.", 9.0f, pastasDish2Reviews);

        List<Review> pastasDish3Reviews = new ArrayList<>();
        pastasDish3Reviews.add(ReviewFactory.createReview("Dish", "6", "Tiramisú casero con café y licor.", 4.5f, 4.5f));
        Dish pastasDish3 = new Dish("6", "Tiramisú", "Tiramisú casero con café y licor.", 6.0f, pastasDish3Reviews);

        Menu pastasMenu = new Menu("2", List.of(pastasDish1, pastasDish2, pastasDish3));

        List<Review> pastasReviews = new ArrayList<>();
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "4", "La pasta estaba deliciosa, muy recomendado.", 4.5f, 4.0f, 4.5f));
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "5", "La atención fue excelente, volvería a ir.", 4.0f, 4.0f, 4.0f));
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "6", "La pasta estaba muy rica, el lugar es muy acogedor.", 4.0f, 4.0f, 4.0f));

        Restaurant pastas = new Restaurant(2, "Casa de Pastas Don Pepe", "Recoleta, Buenos Aires", pastasMenu, pastasReviews);

        // Crear platos para "Cocina Urbana"
        List<Review> urbanaDish1Reviews = new ArrayList<>();
        urbanaDish1Reviews.add(ReviewFactory.createReview("Dish", "7", "Hamburguesa de carne con queso cheddar y panceta.", 4.0f, 4.0f));
        Dish urbanaDish1 = new Dish("7", "Hamburguesa con cheddar", "Hamburguesa de carne con queso cheddar y panceta.", 12.0f, urbanaDish1Reviews);

        List<Review> urbanaDish2Reviews = new ArrayList<>();
        urbanaDish2Reviews.add(ReviewFactory.createReview("Dish", "8", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 4.5f, 4.5f));
        Dish urbanaDish2 = new Dish("8", "Ensalada de pollo", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 8.0f, urbanaDish2Reviews);

        List<Review> urbanaDish3Reviews = new ArrayList<>();
        urbanaDish3Reviews.add(ReviewFactory.createReview("Dish", "9", "Brownie de chocolate con helado de vainilla.", 4.5f, 4.5f));
        Dish urbanaDish3 = new Dish("9", "Brownie con helado", "Brownie de chocolate con helado de vainilla.", 7.0f, urbanaDish3Reviews);

        Menu urbanaMenu = new Menu("3", List.of(urbanaDish1, urbanaDish2, urbanaDish3));

        List<Review> urbanaReviews = new ArrayList<>();
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "7", "La hamburguesa estaba deliciosa, muy recomendado.", 4.0f, 4.0f, 4.0f));
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "8", "La ensalada estaba muy rica, el lugar es muy acogedor.", 4.5f, 4.5f, 4.5f));
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "9", "El brownie estaba delicioso, volvería a ir.", 4.5f, 4.5f, 4.5f));

        Restaurant urbana = new Restaurant(3, "Cocina Urbana", "Palermo, Buenos Aires", urbanaMenu, urbanaReviews);

        // Agregar restaurantes al repositorio
        this.addRestaurant(1, parrilla);
        this.addRestaurant(2, pastas);
        this.addRestaurant(3, urbana);

        System.out.println("Repositorio inicializado con todos los datos completos.");
    }
}

