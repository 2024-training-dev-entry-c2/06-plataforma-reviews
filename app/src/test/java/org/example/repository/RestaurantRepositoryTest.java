package org.example.repository;

import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantRepositoryTest {

  private RestaurantRepository restaurantRepository;
  private Observer mockObserver;

  @BeforeEach
  void setup() {
    restaurantRepository = RestaurantRepository.getInstance();
    restaurantRepository.clear();
    mockObserver = mock(Observer.class);
    restaurantRepository.addObserver(mockObserver);
  }

  @Test
  @DisplayName("Test Add Restaurant")
  void testAddRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);

    assertEquals(restaurant, restaurantRepository.getRestaurant("Restaurante 1"));
    verify(mockObserver).update("Nuevo restaurante agregado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Add Restaurant - Restaurant Already Exists")
  void testAddRestaurantAlreadyExists() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.addRestaurant(restaurant);
    });

    assertEquals("El restaurante ya existe: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Update Restaurant")
  void testUpdateRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);
    restaurant.setAddress("Calle Nueva 456");

    restaurantRepository.updateRestaurant(restaurant);

    assertEquals("Calle Nueva 456", restaurantRepository.getRestaurant("Restaurante 1").getAddress());
    verify(mockObserver).update("Restaurante actualizado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Update Nonexistent Restaurant")
  void testUpdateNonexistentRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.updateRestaurant(restaurant);
    });

    assertEquals("Restaurante no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Restaurant")
  void testRemoveRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);

    restaurantRepository.removeRestaurant("Restaurante 1");

    assertNull(restaurantRepository.getRestaurant("Restaurante 1"));
    verify(mockObserver).update("Restaurante eliminado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Remove Nonexistent Restaurant")
  void testRemoveNonexistentRestaurant() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.removeRestaurant("Restaurante 1");
    });

    assertEquals("Restaurante no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Review To Restaurant")
  void testAddReviewToRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);

    restaurantRepository.addReviewToRestaurant(review);

    assertTrue(restaurant.getReviews().contains(review));
    verify(mockObserver).update("Nueva review agregada al restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Add Review To Nonexistent Restaurant")
  void testAddReviewToNonexistentRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.addReviewToRestaurant(review);
    });

    assertEquals("Restaurante no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    restaurantRepository.addRestaurant(restaurant);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);

    restaurantRepository.addDishToMenu("Restaurante 1", dish);

    assertTrue(menu.getDishes().contains(dish));
    verify(mockObserver).update("Plato agregado con éxito al menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Add Dish To Menu Nonexistent Restaurant")
  void testAddDishToMenuNonexistentRestaurant() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.addDishToMenu("Restaurante 1", dish);
    });

    assertEquals("Restaurante o menú no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Edit Dish In Menu")
  void testEditDishInMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    restaurantRepository.addRestaurant(restaurant);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);

    restaurantRepository.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);

    assertTrue(menu.getDishes().contains(updatedDish));
    verify(mockObserver).update("Plato editado con éxito en el menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Edit Nonexistent Dish In Menu")
  void testEditNonexistentDishInMenu() {
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);
    });

    assertEquals("Restaurante o menú no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    restaurantRepository.addRestaurant(restaurant);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);

    restaurantRepository.removeDishFromMenu("Restaurante 1", "Plato 1");

    assertFalse(menu.getDishes().contains(dish));
    verify(mockObserver).update("Plato eliminado con éxito del menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Remove Nonexistent Dish From Menu")
  void testRemoveNonexistentDishFromMenu() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.removeDishFromMenu("Restaurante 1", "Plato 1");
    });

    assertEquals("Restaurante o menú no encontrado: Restaurante 1", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Null Observer")
  void testAddNullObserver() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.addObserver(null);
    });

    assertEquals("El observador no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Null Observer")
  void testRemoveNullObserver() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.removeObserver(null);
    });

    assertEquals("El observador no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Notify Observers With Null Message")
  void testNotifyObserversWithNullMessage() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.notifyObservers(null);
    });

    assertEquals("El mensaje no puede ser nulo.", exception.getMessage());
  }
  @Test
  @DisplayName("Test Get Instance")
  void testGetInstance() {
    RestaurantRepository instance1 = RestaurantRepository.getInstance();
    RestaurantRepository instance2 = RestaurantRepository.getInstance();

    assertSame(instance1, instance2);
  }


  @Test
  @DisplayName("Test Get Restaurant Not Found")
  void testGetRestaurantNotFound() {
    assertNull(restaurantRepository.getRestaurant("Restaurante Inexistente"));
  }


  @Test
  @DisplayName("Test Associate Menu To Restaurant")
  void testAssociateMenuToRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");

    restaurantRepository.associateMenuToRestaurant("Restaurante 1", menu);

    assertEquals(menu, restaurantRepository.getRestaurant("Restaurante 1").getMenu());
    verify(mockObserver).update("Menu asociado con éxito al restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Associate Menu To Nonexistent Restaurant")
  void testAssociateMenuToNonexistentRestaurant() {
    MenuModel menu = new MenuModel(new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true), "Menu 1");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.associateMenuToRestaurant("Restaurante Inexistente", menu);
    });

    assertEquals("Restaurante no encontrado: Restaurante Inexistente", exception.getMessage());
  }

  @Test
  @DisplayName("Test Calculate Average Rating Restaurant")
  void testCalculateAverageRatingRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    restaurantRepository.addRestaurant(restaurant);
    RestaurantReviewModel review1 = new RestaurantReviewModel("Cliente 1", 4.0, "Muy bueno", restaurant);
    RestaurantReviewModel review2 = new RestaurantReviewModel("Cliente 2", 5.0, "Excelente", restaurant);
    restaurant.addReview(review1);
    restaurant.addReview(review2);

    Double averageRating = restaurantRepository.calculateAverageRatingRestaurant("Restaurante 1");

    assertEquals(4.5, averageRating);
  }
  @Test
  @DisplayName("Test Add Null Restaurant")
  void testAddNullRestaurant() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.addRestaurant(null);
    });

    assertEquals("El restaurante no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Update Null Restaurant")
  void testUpdateNullRestaurant() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.updateRestaurant(null);
    });

    assertEquals("El restaurante no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Null Review To Restaurant")
  void testAddNullReviewToRestaurant() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.addReviewToRestaurant(null);
    });

    assertEquals("La review no puede ser nula.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Associate Null Menu To Restaurant")
  void testAssociateNullMenuToRestaurant() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.associateMenuToRestaurant("Restaurante 1", null);
    });

    assertEquals("El menú no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Add Dish To Menu with Null Dish")
  void testAddDishToMenuWithNullDish() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.addDishToMenu("Restaurante 1", null);
    });

    assertEquals("El plato no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Edit Dish In Menu with Null Updated Dish")
  void testEditDishInMenuWithNullUpdatedDish() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.editDishInMenu("Restaurante 1", "Plato 1", null);
    });

    assertEquals("El plato actualizado no puede ser nulo.", exception.getMessage());
  }

  @Test
  @DisplayName("Test Remove Dish From Menu with Null Dish Name")
  void testRemoveDishFromMenuWithNullDishName() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      restaurantRepository.removeDishFromMenu("Restaurante 1", null);
    });

    assertEquals("El nombre del plato no puede ser nulo.", exception.getMessage());
  }


  @Test
  @DisplayName("Test Notify Observers")
  void testNotifyObservers() {
    restaurantRepository.notifyObservers("Mensaje de prueba");
    verify(mockObserver).update("Mensaje de prueba");
  }

  @Test
  @DisplayName("Test Get All Restaurants")
  void testGetAllRestaurants() {
    RestaurantModel restaurant1 = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantModel restaurant2 = new RestaurantModel("Restaurante 2", "Avenida Imaginaria 456", false);
    restaurantRepository.addRestaurant(restaurant1);
    restaurantRepository.addRestaurant(restaurant2);

    List<RestaurantModel> restaurants = restaurantRepository.getAllRestaurants();
    assertEquals(2, restaurants.size());
    assertTrue(restaurants.contains(restaurant1));
    assertTrue(restaurants.contains(restaurant2));
  }

  @Test
  @DisplayName("Test Remove Dish From Menu - Dish Not Found")
  void testRemoveDishFromMenuDishNotFound() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    restaurantRepository.addRestaurant(restaurant);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      restaurantRepository.removeDishFromMenu("Restaurante 1", "Plato Inexistente");
    });

    assertEquals("Plato no encontrado en el menú: Plato Inexistente", exception.getMessage());
  }

}