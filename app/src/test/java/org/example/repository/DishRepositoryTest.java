
package org.example.repository;

import org.example.models.DishModel;
import org.example.models.DishReviewModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishRepositoryTest {

  private DishRepository dishRepository;
  private Observer mockObserver;

  @BeforeEach
  void setup() {
    dishRepository = DishRepository.getInstance();
    dishRepository.clear();
    mockObserver = mock(Observer.class);
    dishRepository.addObserver(mockObserver);
  }

  @Test
  @DisplayName("Test Add Dish")
  void testAddDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDish(dish);

    assertEquals(dish, dishRepository.getDish("Plato 1"));
    verify(mockObserver).update("Nuevo plato agregado: Plato 1");
  }

  @Test
  @DisplayName("Test Add Dish - Dish Already Exists")
  void testAddDishAlreadyExists() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDish(dish);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      dishRepository.addDish(dish);
    });

    assertEquals("El plato ya existe: Plato 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Review To Dish")
  void testAddReviewToDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDish(dish);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);

    dishRepository.addReviewToDish(review);

    assertTrue(dish.getReviews().contains(review));
    verify(mockObserver).update("Nueva review agregada al plato: Plato 1");
  }

  @Test
  @DisplayName("Test Add Review To Nonexistent Dish")
  void testAddReviewToNonexistentDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      dishRepository.addReviewToDish(review);
    });

    assertEquals("El plato no existe: Plato 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDishToMenu("Restaurante 1", dish);

    verify(mockObserver).update("Plato agregado con éxito al menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Edit Dish In Menu")
  void testEditDishInMenu() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDish(dish);
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);

    dishRepository.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);

    assertEquals(updatedDish, dishRepository.getDish("Plato 1"));
    verify(mockObserver).update("Plato editado con éxito en el menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Edit Nonexistent Dish In Menu")
  void testEditNonexistentDishInMenu() {
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      dishRepository.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);
    });

    assertEquals("Plato no encontrado: Plato 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    dishRepository.addDish(dish);

    dishRepository.removeDishFromMenu("Restaurante 1", "Plato 1");

    assertNull(dishRepository.getDish("Plato 1"));
    verify(mockObserver).update("Plato eliminado con éxito del menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Remove Nonexistent Dish From Menu")
  void testRemoveNonexistentDishFromMenu() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      dishRepository.removeDishFromMenu("Restaurante 1", "Plato 1");
    });

    assertEquals("Plato no encontrado: Plato 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Null Observer")
  void testAddNullObserver() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      dishRepository.addObserver(null);
    });

    assertEquals("El observador no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Null Observer")
  void testRemoveNullObserver() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      dishRepository.removeObserver(null);
    });

    assertEquals("El observador no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Notify Observers With Null Message")
  void testNotifyObserversWithNullMessage() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      dishRepository.notifyObservers(null);
    });

    assertEquals("El mensaje no puede ser nulo.", exception.getMessage());
  }
}