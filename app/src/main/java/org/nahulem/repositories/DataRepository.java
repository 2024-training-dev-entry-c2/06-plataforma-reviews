package org.nahulem.repositories;

import org.nahulem.factories.ReviewFactory;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.models.Review;

import java.util.ArrayList;
import java.util.Collection;
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
        return new HashMap<>(restaurants);
    }

    public Boolean deleteRestaurant(Integer restaurantId) {
        return restaurants.remove(restaurantId) != null;
    }

    public void updateRestaurant(Restaurant updatedRestaurant) {
        restaurants.put(updatedRestaurant.getRestaurantId(), updatedRestaurant);
    }

    public void addMenu(Menu menu) {
        menus.put(menu.getMenuId(), menu);
    }

    public Menu getMenu(Integer id) {
        return menus.get(id);
    }

    public Collection<Menu> getAllMenus() {
        return menus.values();
    }

    public void deleteMenu(Integer id) {
        menus.remove(id);
    }

    public void addDish(Dish dish) {
        dishes.put(dish.getDishId(), dish);
    }

    public Dish getDish(Integer id) {
        return dishes.get(id);
    }

    public Collection<Dish> getAllDishes() {
        return dishes.values();
    }

    public void deleteDish(Integer id) {
        dishes.remove(id);
    }

    private void initialize() {
        List<Review> parrillaDish1Reviews = new ArrayList<>();
        parrillaDish1Reviews.add(ReviewFactory.createReview("Dish", "1", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f, 5.0f));
        Dish parrillaDish1 = new Dish("Empanadas de carne", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f, parrillaDish1Reviews);


        List<Review> parrillaDish2Reviews = new ArrayList<>();
        parrillaDish2Reviews.add(ReviewFactory.createReview("Dish", "2", "Corte clásico argentino cocido a la parrilla.", 4.0f, 4.5f));
        Dish parrillaDish2 = new Dish("Asado de tira", "Corte clásico argentino cocido a la parrilla.", 15.0f, parrillaDish2Reviews);

        List<Review> parrillaDish3Reviews = new ArrayList<>();
        parrillaDish3Reviews.add(ReviewFactory.createReview("Dish", "3", "Flan casero acompañado de dulce de leche artesanal.", 5.0f, 4.5f));
        Dish parrillaDish3 = new Dish("Flan con dulce de leche", "Flan casero acompañado de dulce de leche artesanal.", 5.0f, parrillaDish3Reviews);

        Menu parrillaMenu = new Menu("Parrilla", List.of(parrillaDish1, parrillaDish2, parrillaDish3));

        List<Review> parrillaReviews = new ArrayList<>();
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "1", "El mejor asado que he probado en mi vida.", 5.0f, 4.3f, 5.0f));
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "2", "La atención fue excelente, muy recomendado.", 4.5f, 4.0f, 4.5f));
        parrillaReviews.add(ReviewFactory.createReview("Restaurant", "3", "La parrilla es un clásico de Buenos Aires, muy buena.", 4.0f, 4.0f, 4.0f));

        Restaurant parrilla = new Restaurant("La Parrilla Porteña", "Hermosa parrillada en barrio clasico de Buenos Aires", "San Telmo, Buenos Aires", parrillaMenu);

        // Crear platos para "Casa de Pastas Don Pepe"
        List<Review> pastasDish1Reviews = new ArrayList<>();
        pastasDish1Reviews.add(ReviewFactory.createReview("Dish", "4", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 4.5f, 4.0f));
        Dish pastasDish1 = new Dish("Ravioles de ricota y espinaca", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 10.0f, pastasDish1Reviews);

        List<Review> pastasDish2Reviews = new ArrayList<>();
        pastasDish2Reviews.add(ReviewFactory.createReview("Dish", "5", "Fideos caseros con salsa bolognesa y queso rallado.", 4.0f, 4.0f));
        Dish pastasDish2 = new Dish("Fideos a la bolognesa", "Fideos caseros con salsa bolognesa y queso rallado.", 9.0f, pastasDish2Reviews);

        List<Review> pastasDish3Reviews = new ArrayList<>();
        pastasDish3Reviews.add(ReviewFactory.createReview("Dish", "6", "Tiramisú casero con café y licor.", 4.5f, 4.5f));
        Dish pastasDish3 = new Dish("Tiramisú", "Tiramisú casero con café y licor.", 6.0f, pastasDish3Reviews);

        Menu pastasMenu = new Menu("Pastas", List.of(pastasDish1, pastasDish2, pastasDish3));

        List<Review> pastasReviews = new ArrayList<>();
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "4", "La pasta estaba deliciosa, muy recomendado.", 4.5f, 4.0f, 4.5f));
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "5", "La atención fue excelente, volvería a ir.", 4.0f, 4.0f, 4.0f));
        pastasReviews.add(ReviewFactory.createReview("Restaurant", "6", "La pasta estaba muy rica, el lugar es muy acogedor.", 4.0f, 4.0f, 4.0f));

        Restaurant pastas = new Restaurant("Casa de Pastas Don Pepe", "Pastas caseras en Buenos Aires", "Recoleta, Buenos Aires", pastasMenu);

        // Crear platos para "Cocina Urbana"
        List<Review> urbanaDish1Reviews = new ArrayList<>();
        urbanaDish1Reviews.add(ReviewFactory.createReview("Dish", "7", "Hamburguesa de carne con queso cheddar y panceta.", 4.0f, 4.0f));
        Dish urbanaDish1 = new Dish("Hamburguesa con cheddar", "Hamburguesa de carne con queso cheddar y panceta.", 12.0f, urbanaDish1Reviews);

        List<Review> urbanaDish2Reviews = new ArrayList<>();
        urbanaDish2Reviews.add(ReviewFactory.createReview("Dish", "8", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 4.5f, 4.5f));
        Dish urbanaDish2 = new Dish("Ensalada de pollo", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 8.0f, urbanaDish2Reviews);

        List<Review> urbanaDish3Reviews = new ArrayList<>();
        urbanaDish3Reviews.add(ReviewFactory.createReview("Dish", "9", "Brownie de chocolate con helado de vainilla.", 4.5f, 4.5f));
        Dish urbanaDish3 = new Dish("Brownie con helado", "Brownie de chocolate con helado de vainilla.", 7.0f, urbanaDish3Reviews);

        Menu urbanaMenu = new Menu("Comida Urbana", List.of(urbanaDish1, urbanaDish2, urbanaDish3));

        List<Review> urbanaReviews = new ArrayList<>();
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "7", "La hamburguesa estaba deliciosa, muy recomendado.", 4.0f, 4.0f, 4.0f));
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "8", "La ensalada estaba muy rica, el lugar es muy acogedor.", 4.5f, 4.5f, 4.5f));
        urbanaReviews.add(ReviewFactory.createReview("Restaurant", "9", "El brownie estaba delicioso, volvería a ir.", 4.5f, 4.5f, 4.5f));

        Restaurant urbana = new Restaurant("Cocina Urbana", "Comida rápida y saludable en Buenos Aires", "Palermo, Buenos Aires", urbanaMenu);

        // Agregar restaurantes al repositorio
        this.addRestaurant(parrilla);
        this.addRestaurant(pastas);
        this.addRestaurant(urbana);

        System.out.println("Repositorio inicializado con todos los datos completos.");
    }

    public Map<Integer, Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Integer, Dish> dishes) {
        this.dishes = dishes;
    }

    public static void setInstance(DataRepository instance) {
        DataRepository.instance = instance;
    }

    public Map<Integer, Menu> getMenus() {
        return menus;
    }

    public void setMenus(Map<Integer, Menu> menus) {
        this.menus = menus;
    }

    public Map<Integer, Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Map<Integer, Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}

