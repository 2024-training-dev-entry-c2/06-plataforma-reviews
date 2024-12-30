package org.example;

import org.example.models.Dish;
import org.example.repositories.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DishRepositoryTest {
  private DishRepository dishRepository;

  @BeforeEach
  void setUp() {
    dishRepository = DishRepository.getInstance(); // Obtener la instancia de DishRepository
  }

  @Test
  void testAddDish() {
    Long id = 1L;
    Long menuId = 10L;
    String name = "Pollo";
    String description = "Delicioso plato de pollo";
    Long price = 15000L;

    dishRepository.addDish(id, menuId, name, description, price);

    Dish dish = dishRepository.getDish(id);
    assertNotNull(dish);
    assertEquals(name, dish.getName());
    assertEquals(description, dish.getDescription());
    assertEquals(price, dish.getPrice());
  }

  @Test
  void testUpdateDish() {
    Long id = 1L;
    Long menuId = 10L;
    String name = "Pollo";
    String description = "Delicioso pollo al horno";
    Long price = 15000L;

    dishRepository.addDish(id, menuId, name, description, price);

    String newName = "Pollo Asado";
    String newDescription = "Pollo asado con salsa BBQ";
    Long newPrice = 17000L;
    Dish dish = dishRepository.getDish(id);
    dishRepository.updateDish(dish, newName, newDescription, newPrice);

    assertEquals(newName, dish.getName());
    assertEquals(newDescription, dish.getDescription());
    assertEquals(newPrice, dish.getPrice());
  }

  @Test
  void testRemoveDish() {
    Long id = 1L;
    Long menuId = 10L;
    String name = "Pollo";
    String description = "Delicioso plato de pollo";
    Long price = 15000L;

    dishRepository.addDish(id, menuId, name, description, price);

    Boolean removed = dishRepository.removeDish(id);

    assertTrue(removed);
    assertNull(dishRepository.getDish(id));
  }

  @Test
  void testGetDish() {
    Long id = 1L;
    Long menuId = 10L;
    String name = "Pollo";
    String description = "Delicioso plato de pollo";
    Long price = 15000L;

    dishRepository.addDish(id, menuId, name, description, price);

    Dish dish = dishRepository.getDish(id);
    assertNotNull(dish);
    assertEquals(id, dish.getId());
    assertEquals(name, dish.getName());
    assertEquals(description, dish.getDescription());
    assertEquals(price, dish.getPrice());
  }

}
