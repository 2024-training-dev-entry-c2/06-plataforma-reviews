package org.example;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.repositories.MenuRepository;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MenuServiceTest {

  private MenuRepository menuRepositoryMock;
  private MenuService menuService;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    menuRepositoryMock = Mockito.mock(MenuRepository.class);
    menuService = new MenuService(menuRepositoryMock);
  }

  @Test
  void testAddDishMenu_MenuExists() {
    Long menuId = 1L;
    String name = "Pollo Asado";
    String description = "Delicioso pollo asado";
    Long price = 15000L;

    when(menuRepositoryMock.getMenu(menuId)).thenReturn(new Menu(menuId));

    menuService.addDishMenu(menuId, name, description, price);

    verify(menuRepositoryMock).addDishMenu(menuId, name, description, price);
  }

  @Test
  void testAddDishMenu_MenuDoesNotExist() {
    Long menuId = 2L;
    String name = "Ensalada";
    String description = "Ensalada fresca";
    Long price = 8000L;

    when(menuRepositoryMock.getMenu(menuId)).thenReturn(null);

    menuService.addDishMenu(menuId, name, description, price);

    verify(menuRepositoryMock, never()).addDishMenu(anyLong(), anyString(), anyString(), anyLong());
  }

  @Test
  void testUpdateDishMenu_DishExists() {
    Long dishId = 1L;
    String name = "Pollo Guisado";
    String description = "Pollo cocido con especias";
    Long price = 20000L;
    Dish dish = new Dish(dishId, 2L,"Pollo", "Plato original", 15000L);

    when(menuRepositoryMock.getDish(dishId)).thenReturn(dish);

    menuService.updateDishMenu(dishId, name, description, price);

    verify(menuRepositoryMock).updateDishMenu(dish, name, description, price);
  }

  @Test
  void testUpdateDishMenu_DishDoesNotExist() {
    Long dishId = 2L;
    String name = "Sopa de Verduras";
    String description = "Sopa saludable";
    Long price = 10000L;

    when(menuRepositoryMock.getDish(dishId)).thenReturn(null);

    menuService.updateDishMenu(dishId, name, description, price);

    verify(menuRepositoryMock, never()).updateDishMenu(any(Dish.class), anyString(), anyString(), anyLong());
  }

  @Test
  void testRemoveDishMenu_DishRemovedSuccessfully() {
    Long dishId = 1L;
    Long menuId = 1L;

    when(menuRepositoryMock.removeDishMenu(dishId, menuId)).thenReturn(true);

    menuService.removeDishMenu(dishId, menuId);

    verify(menuRepositoryMock).removeDishMenu(dishId, menuId);
  }

  @Test
  void testRemoveDishMenu_DishNotRemoved() {
    Long dishId = 2L;
    Long menuId = 1L;

    when(menuRepositoryMock.removeDishMenu(dishId, menuId)).thenReturn(false);

    menuService.removeDishMenu(dishId, menuId);

    verify(menuRepositoryMock).removeDishMenu(dishId, menuId);
  }

}

