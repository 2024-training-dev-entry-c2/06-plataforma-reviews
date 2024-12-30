package org.example.services;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MenuServiceTest {
  private RestaurantRepository mockRepository;
  private MenuService menuService;
  private Restaurant mockRestaurant;
  private Menu mockMenu;

  @BeforeEach
  void setup(){
    mockRepository = mock(RestaurantRepository.class);
    mockRestaurant = mock(Restaurant.class);
    mockMenu = mock(Menu.class);
    menuService = new MenuService(mockRepository);
  }

  @Test
  @DisplayName("Agregar menú")
  void testAddMenu(){
    String restaurantName = "Test Restaurant";
    String menuName = "Test menu";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);

    Boolean result = menuService.addMenu(restaurantName, menuName);

    assertTrue(result);
    verify(mockRestaurant).setMenu(any(Menu.class));
  }

  @Test
  @DisplayName("Agregar menú a restaurante null")
  void testFailAddMenu(){
    String restaurantName = "Test Restaurant";
    String menuName = "Test menu";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(null);

    Boolean result = menuService.addMenu(restaurantName, menuName);

    assertFalse(result);
  }

  @Test
  @DisplayName("Obtener menú de restaurante")
  void testGetMenu(){
    String restaurantName = "Test Restaurant";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);

    Menu menu = menuService.getMenuByRestaurantName(restaurantName);

    assertNotNull(menu);
    assertEquals(mockMenu, menu);
  }
}