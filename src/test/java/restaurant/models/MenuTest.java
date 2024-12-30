package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    private Menu menu;
    private Dish dish;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("Restaurante de Prueba", "123 Calle Prueba");
        menu = new Menu(restaurant);
        dish = new Dish("Pasta", "Deliciosa pasta con salsa de tomate", 12.99);
    }

    @Test
    public void testAddDish() {
        menu.addDish(dish);

        assertTrue(menu.getDishes().contains(dish));
    }

    @Test
    public void testRemoveDish() {
        menu.addDish(dish);
        menu.removeDish(dish);

        assertTrue(!menu.getDishes().contains(dish));
    }

    @Test
    public void testGetDishes() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        menu.setDishes(dishes);

        assertEquals(dishes, menu.getDishes());
    }

    @Test
    public void testAddReview() {
        DishReview review = new DishReview("¡Sabroso!", 4.5, dish);
        menu.addDish(dish);
        menu.addReview(review);

        assertTrue(dish.getReviews().contains(review));
    }

    @Test
    public void testAddObserver() {
        IObserver observer = message -> {};
        menu.addObserver(observer);

        assertTrue(menu.getObservers().contains(observer));
    }

    @Test
    public void testNotifyObservers() {
        IObserver observer = message -> assertEquals("New review added to dish: Pasta", message);
        menu.addObserver(observer);
        DishReview review = new DishReview("¡Sabroso!", 4.5, dish);
        menu.addDish(dish);
        menu.addReview(review);
    }

    @Test
    public void testGetRestaurant() {
        assertEquals(restaurant, menu.getRestaurant());
    }

    @Test
    public void testSetRestaurant() {
        Restaurant newRestaurant = new Restaurant("Nuevo Restaurante", "456 Calle Nueva");
        menu.setRestaurant(newRestaurant);

        assertEquals(newRestaurant, menu.getRestaurant());
    }

    @Test
    public void testNotifyObserversDirectly() {
        IObserver observer = message -> assertEquals("Test message", message);
        menu.addObserver(observer);
        menu.notifyObservers("Test message");
    }
}