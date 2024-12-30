package org.nahulem.repositories;

import org.nahulem.models.Dish;
import org.nahulem.models.DishReview;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRepository {
    private static DataRepository instance;
    private Map<Integer, Restaurant> restaurants;
    private Map<Integer, Dish> dishes;
    private Map<Integer, Menu> menus;

    private DataRepository() {
        this.restaurants = new HashMap<>();
        this.dishes = new HashMap<>();
        this.menus = new HashMap<>();
        initialize();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getRestaurantId(), restaurant);
    }

    public Restaurant getRestaurant(Integer id) {
        return restaurants.get(id);
    }

    public Map<Integer, Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public Boolean deleteRestaurant(Integer restaurantId) {
        return restaurants.remove(restaurantId) != null;
    }

    public Boolean updateRestaurant(Restaurant updatedRestaurant) {
        Integer restaurantId = updatedRestaurant.getRestaurantId();
        if (restaurants.containsKey(restaurantId)) {
            restaurants.put(restaurantId, updatedRestaurant);
            return true;
        }
        return false;
    }

    public void addMenu(Menu menu) {
        menus.put(menu.getMenuId(), menu);
    }

    public void addDish(Dish dish) {
        dishes.put(dish.getDishId(), dish);
    }

    public Dish getDish(Integer id) {
        return dishes.get(id);
    }

    private void initialize() {
        Dish parrillaDish1 = new Dish("Empanadas de carne", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f);
        Dish parrillaDish2 = new Dish("Asado de tira", "Corte clásico argentino cocido a la parrilla.", 15.0f);
        Dish parrillaDish3 = new Dish("Flan con dulce de leche", "Flan casero acompañado de dulce de leche artesanal.", 5.0f);
        addDish(parrillaDish1);
        addDish(parrillaDish2);
        addDish(parrillaDish3);

        Dish pastasDish1 = new Dish("Ravioles de ricota y espinaca", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 10.0f);
        Dish pastasDish2 = new Dish("Fideos a la bolognesa", "Fideos caseros con salsa bolognesa y queso rallado.", 9.0f);
        Dish pastasDish3 = new Dish("Tiramisú", "Tiramisú casero con café y licor.", 6.0f);
        addDish(pastasDish1);
        addDish(pastasDish2);
        addDish(pastasDish3);

        Dish urbanaDish1 = new Dish("Hamburguesa con cheddar", "Hamburguesa de carne con queso cheddar y panceta.", 12.0f);
        Dish urbanaDish2 = new Dish("Ensalada de pollo", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 8.0f);
        Dish urbanaDish3 = new Dish("Brownie con helado", "Brownie de chocolate con helado de vainilla.", 7.0f);
        addDish(urbanaDish1);
        addDish(urbanaDish2);
        addDish(urbanaDish3);

        Menu parrillaMenu = new Menu("Parrilla", new ArrayList<>(List.of(
                getDish(parrillaDish1.getDishId()),
                getDish(parrillaDish2.getDishId()),
                getDish(parrillaDish3.getDishId())
        )));
        Menu pastasMenu = new Menu("Pastas", new ArrayList<>(List.of(
                getDish(pastasDish1.getDishId()),
                getDish(pastasDish2.getDishId()),
                getDish(pastasDish3.getDishId())
        )));
        Menu urbanaMenu = new Menu("Comida Urbana", new ArrayList<>(List.of(
                getDish(urbanaDish1.getDishId()),
                getDish(urbanaDish2.getDishId()),
                getDish(urbanaDish3.getDishId())
        )));

        Restaurant parrilla = new Restaurant("La Parrilla Porteña", "Hermosa parrillada en barrio clásico de Buenos Aires", "San Telmo, Buenos Aires", parrillaMenu);
        Restaurant pastas = new Restaurant("Casa de Pastas Don Pepe", "Pastas caseras en Buenos Aires", "Recoleta, Buenos Aires", pastasMenu);
        Restaurant urbana = new Restaurant("Cocina Urbana", "Comida rápida y saludable en Buenos Aires", "Palermo, Buenos Aires", urbanaMenu);

        parrillaDish1.addReview(new DishReview("Sabor increíble, me encantaron.", 4.8f, 4.9f));
        parrillaDish2.addReview(new DishReview("Excelente corte de carne.", 5.0f, 4.8f));
        parrillaDish3.addReview(new DishReview("El flan estaba delicioso.", 4.5f, 4.7f));

        pastasDish1.addReview(new DishReview("La pasta estaba perfectamente cocida.", 4.7f, 4.8f));
        pastasDish2.addReview(new DishReview("La bolognesa estaba muy buena.", 4.6f, 4.5f));
        pastasDish3.addReview(new DishReview("El tiramisú es espectacular.", 4.9f, 5.0f));

        urbanaDish1.addReview(new DishReview("La hamburguesa estaba deliciosa.", 4.7f, 4.8f));
        urbanaDish2.addReview(new DishReview("Fresca y bien servida.", 4.5f, 4.4f));
        urbanaDish3.addReview(new DishReview("Perfecto balance de dulce.", 4.8f, 4.9f));

        parrilla.addReview(new RestaurantReview("Excelente atención y calidad.", 5.0f, 4.8f, 4.9f));
        parrilla.addReview(new RestaurantReview("Muy buena parrilla, pero algo cara.", 4.5f, 4.4f, 4.3f));

        pastas.addReview(new RestaurantReview("Las pastas estaban excelentes.", 4.9f, 5.0f, 4.8f));
        pastas.addReview(new RestaurantReview("Ambiente acogedor.", 4.7f, 4.5f, 4.6f));

        urbana.addReview(new RestaurantReview("Rápido y muy rico.", 4.8f, 4.7f, 4.6f));
        urbana.addReview(new RestaurantReview("Me encantó el ambiente moderno.", 4.6f, 4.5f, 4.7f));

        addRestaurant(parrilla);
        addRestaurant(pastas);
        addRestaurant(urbana);
    }

}

