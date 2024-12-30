package org.example;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.utils.consoleUtils.ConsoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class AppTest {

    private ConsoleUtils console;
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        console = mock(ConsoleUtils.class);
        menuRepository = MenuRepository.getInstance();
    }

    @Test
    void testMain() {
        // Arrange: Set up expected behavior for mocks
        when(console.getString("Entra el nombre del restaurante: ")).thenReturn("La Pizzeria", "La Pizzeria");
        when(console.getString("Entra el teléfono del restaurante: ")).thenReturn("123456789");
        when(console.getString("Entra la dirección del restaurante: ")).thenReturn("123 Main Street");
        when(console.getInteger("Entra la disponibilidad del restaurante: ")).thenReturn(20);

        // Act: Call the main method
        App.main(new String[]{});

        // Assert: Verify interactions and expected outcomes for adding a restaurant
        verify(console).getString("Entra el nombre del restaurante: ");
        verify(console).getString("Entra el teléfono del restaurante: ");
        verify(console).getString("Entra la dirección del restaurante: ");
        verify(console).getInteger("Entra la disponibilidad del restaurante: ");

        // Assert: Verify interactions and expected outcomes for removing a restaurant
        verify(console).getString("Entra el nombre del restaurante que deseas eliminar: ");
    }

    @Test
    void testDisplayRestaurants() {
        // Arrange: Set up expected behavior for mocks
        when(console.getString("Entra el nombre del restaurante: ")).thenReturn("La Pizzeria");
        when(console.getString("Entra el teléfono del restaurante: ")).thenReturn("123456789");
        when(console.getString("Entra la dirección del restaurante: ")).thenReturn("123 Main Street");
        when(console.getInteger("Entra la disponibilidad del restaurante: ")).thenReturn(20);

        // Act: Call the main method
        App.main(new String[]{});

        // Assert: Verify interactions and expected outcomes for displaying restaurants
        verify(console, times(2)).getString("Entra el nombre del restaurante: ");
        verify(console, times(2)).getString("Entra el teléfono del restaurante: ");
        verify(console, times(2)).getString("Entra la dirección del restaurante: ");
        verify(console, times(2)).getInteger("Entra la disponibilidad del restaurante: ");
    }

    @Test
    void testRestaurantModel() {
        // Test constructor and getters
        Restaurant restaurant = new Restaurant("La Pizzeria", "123456789", "123 Main Street", 20, null, Collections.emptyList(), 4.5);
        assertEquals("La Pizzeria", restaurant.getName());
        assertEquals("123456789", restaurant.getPhone());
        assertEquals("123 Main Street", restaurant.getAddress());
        assertEquals(20, restaurant.getAvailable());
        assertNull(restaurant.getMenu());
        assertEquals(0, restaurant.getReviews().size());
        assertEquals(4.5, restaurant.getRaiting());

        // Test setters
        restaurant.setName("New Name");
        restaurant.setPhone("987654321");
        restaurant.setAddress("456 Another Street");
        restaurant.setAvailable(10);
        restaurant.setMenu(null);
        restaurant.setReviews(Collections.emptyList());
        restaurant.setRaiting(3.5);

        assertEquals("New Name", restaurant.getName());
        assertEquals("987654321", restaurant.getPhone());
        assertEquals("456 Another Street", restaurant.getAddress());
        assertEquals(10, restaurant.getAvailable());
        assertNull(restaurant.getMenu());
        assertEquals(0, restaurant.getReviews().size());
        assertEquals(3.5, restaurant.getRaiting());
    }

    @Test
    void testMenuModel() {
        // Test constructor and getters
        Restaurant restaurant = new Restaurant("La Pizzeria", "123456789", "123 Main Street", 20, null, Collections.emptyList(), 4.5);
        Menu menu = new Menu(restaurant, new LinkedList<>());
        assertEquals(restaurant, menu.getRestaurant());
        assertEquals(0, menu.getDishes().size());

        // Test setters
        Dish dish = new Dish("Pizza", "Delicious cheese pizza", 9.99, Collections.emptyList());
        menu.getDishes().add(dish);
        assertEquals(1, menu.getDishes().size());
        assertEquals(dish, menu.getDishes().get(0));
    }

    @Test
    void testAddDishToMenu() {
        // Arrange
        Restaurant restaurant = new Restaurant("La Pizzeria", "123456789", "123 Main Street", 20, null, Collections.emptyList(), 4.5);
        Dish dish = new Dish("Pizza", "Delicious cheese pizza", 9.99, Collections.emptyList());

        // Act
        menuRepository.addDishToMenu(restaurant, dish);

        // Assert
        Menu menu = menuRepository.getMenuByRestaurant(restaurant);
        assertEquals(1, menu.getDishes().size());
        assertEquals(dish, menu.getDishes().get(0));
    }

    @Test
    void testRemoveDishFromMenu() {
        // Arrange
        Restaurant restaurant = new Restaurant("La Pizzeria", "123456789", "123 Main Street", 20, null, Collections.emptyList(), 4.5);
        Dish dish = new Dish("Pizza", "Delicious cheese pizza", 9.99, Collections.emptyList());
        menuRepository.addDishToMenu(restaurant, dish);

        // Act
        menuRepository.removeDishFromMenu(restaurant, dish);

        // Assert
        Menu menu = menuRepository.getMenuByRestaurant(restaurant);
        assertEquals(0, menu.getDishes().size());
    }
}