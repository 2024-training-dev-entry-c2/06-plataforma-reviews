package org.example.services;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishServiceTest {
  private DishService dishService;
  private RestaurantRepository mockRepository;
  private Restaurant mockRestaurant;
  private Menu mockMenu;

  @BeforeEach
  void setUp() {
    mockRepository = mock(RestaurantRepository.class);
    mockRestaurant = mock(Restaurant.class);
    mockMenu = mock(Menu.class);

    dishService = new DishService(mockRepository);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
  }

  @Test
  @DisplayName("Agregar plato exitosamente")
  void testAddDish() {
    when(mockRepository.getRestaurantByName("Restaurante de testeo")).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);

    Boolean result = dishService.addDish("Restaurante de testeo", "Pizza", "Porción de pizza", 15000f);

    assertTrue(result);
    verify(mockMenu).addDish(any(Dish.class));
  }

  @Test
  @DisplayName("Agregar plato en restaurante null")
  void testAddDish_RestaurantNotFound() {
    String restaurantName = "Restaurante desconocido";
    String dishName = "Pasta";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(null);

    boolean result = dishService.addDish(restaurantName, dishName, null, null);

    assertFalse(result);
    verify(mockMenu, never()).addDish(any());
  }

  @Test
  @DisplayName("Eliminar plato")
  void testRemoveDish() {
    String restaurantName = "Restaurante de testeo";
    Dish dish = new Dish("Pizza", "Porción de pizza", 15000f);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockMenu.getDishes()).thenReturn(Map.of(dish.getName(), dish));

    boolean result = dishService.removeDish(restaurantName, dish);

    assertTrue(result);
    verify(mockMenu).removeDish(dish);
  }

  @Test
  @DisplayName("Eliminar plato en restaurante no encontrado")
  void testRemoveDish_RestaurantNotFound() {
    String restaurantName = "Restaurante desconocido";
    Dish dish = new Dish("Pizza", "Porción de pizza", 15000f);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(null);

    boolean result = dishService.removeDish(restaurantName, dish);

    assertFalse(result);
    verify(mockMenu, never()).removeDish(any());
  }

  @Test
  @DisplayName("Editar plato")
  void testEditDish_Success() {
    String restaurantName ="Restaurante de testeo";
    Dish existingDish = new Dish("Pizza", "Porción de pizza", 15000f);
    String newDishName = "Nueva Pizza";
    String newDescription = "Otra nueva pizza";
    Float newPrice = 18000f;

    Map<String, Dish> mockDishMap = new HashMap<>();
    mockDishMap.put(existingDish.getName(), existingDish);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockMenu.getDishes()).thenReturn(mockDishMap);
    boolean result = dishService.editDish(restaurantName, existingDish, newDishName, newDescription, newPrice);

    assertTrue(result);
  }

  @Test
  void testEditDish_DishNotFound() {
    String restaurantName = "Restaurante de testeo";
    Dish nonExistentDish = new Dish("No existe", "No descripción", 0.0f);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockMenu.getDishes()).thenReturn(Map.of());

    boolean result = dishService.editDish(restaurantName, nonExistentDish, "Nuevo nombre", "Nueva descripción", 20000f);

    assertFalse(result);
  }

  @Test
  void testGetDishByName_Success() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Pizza";
    Dish dish = new Dish(dishName, "Porción de pizza", 15000f);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockMenu.searchDish(dishName)).thenReturn(dish);

    Dish result = dishService.getDishByName(restaurantName, dishName);

    assertNotNull(result);
    assertEquals(dishName, result.getName());
  }

  @Test
  void testGetDishByName_DishNotFound() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Plato no existente";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockMenu.searchDish(dishName)).thenReturn(null);

    Dish result = dishService.getDishByName(restaurantName, dishName);

    assertNull(result);
  }

}