package org.nahulem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DataRepositoryTest {
    private DataRepository dataRepository;

    @BeforeEach
    void setUp() {
        dataRepository = DataRepository.getInstance();
    }

    @Test
    void getAllRestaurants() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        dataRepository.addRestaurant(restaurant);

        assertEquals(restaurant, dataRepository.getAllRestaurants().get(restaurant.getRestaurantId()));
    }

    @Test
    void deleteRestaurant() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        dataRepository.addRestaurant(restaurant);

        assertTrue(dataRepository.deleteRestaurant(restaurant.getRestaurantId()));
        assertFalse(dataRepository.deleteRestaurant(restaurant.getRestaurantId()));
    }

    @Test
    void updateRestaurantWhenExists() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        dataRepository.addRestaurant(restaurant);

        Restaurant updatedRestaurant = new Restaurant("Restaurante Actualizado", "Descripcion Actualizada", "Ubicacion Actualizada", mockMenu);
        updatedRestaurant.setRestaurantId(restaurant.getRestaurantId());

        assertTrue(dataRepository.updateRestaurant(updatedRestaurant));
        assertEquals("Restaurante Actualizado", dataRepository.getAllRestaurants().get(restaurant.getRestaurantId()).getName());
    }

    @Test
    void updateRestaurantWhenNotExists() {
        Restaurant updatedRestaurant = new Restaurant("Restaurante Inexistente", "Descripcion Test", "Ubicacion Test", null);

        assertFalse(dataRepository.updateRestaurant(updatedRestaurant));
    }

    @Test
    void addMenu() {
        Menu menu = new Menu("Menu Test", null);
        dataRepository.addMenu(menu);

        assertNotNull(dataRepository.getAllRestaurants()); // Verificar si el repositorio no se modifica de forma inesperada.
    }

    @Test
    void addDish() {
        Dish dish = new Dish("Plato Test", "Descripcion Test", 10.0f);
        dataRepository.addDish(dish);

        assertEquals(dish, dataRepository.getDish(dish.getDishId()));
    }
}
