package org.example.repository;

import org.example.models.*;
import org.example.repositories.DataRepository;
import org.example.Interface.observable.Observer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataRepositoryTest {

  private DataRepository repository;
  private Observer mockObserver;

  @BeforeEach
  void setUp() {
    repository = DataRepository.getInstance();
    repository.clear();
    mockObserver = mock(Observer.class);
    repository.addObserver(mockObserver);
  }

  @AfterEach
  void tearDown() {
    repository.clear();
  }

  @Test
  @DisplayName("Test Get Instance")
  void testGetInstance() {
    DataRepository instance1 = DataRepository.getInstance();
    DataRepository instance2 = DataRepository.getInstance();
    assertSame(instance1, instance2);
  }

  @Test
  @DisplayName("Test Add Observer")
  void testAddObserver() {
    Observer newObserver = mock(Observer.class);
    repository.addObserver(newObserver);
    repository.notifyObservers("Test message");
    verify(newObserver).update("Test message");
  }

  @Test
  @DisplayName("Test Remove Observer")
  void testRemoveObserver() {
    repository.removeObserver(mockObserver);
    repository.notifyObservers("Test message");
    verify(mockObserver, never()).update("Test message");
  }

  @Test
  @DisplayName("Test Notify Observers")
  void testNotifyObservers() {
    repository.notifyObservers("Test message");
    verify(mockObserver).update("Test message");
  }

  @Test
  @DisplayName("Test Add Restaurant")
  void testAddRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    assertEquals(restaurant, repository.getRestaurant("Restaurante 1"));
    verify(mockObserver).update("Nuevo restaurante agregado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Update Restaurant")
  void testUpdateRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    restaurant.setAddress("Nueva Dirección 456");
    repository.updateRestaurant(restaurant);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertEquals("Nueva Dirección 456", repository.getRestaurant("Restaurante 1").getAddress());
    verify(mockObserver).update("Restaurante actualizado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Get All Restaurants")
  void testGetAllRestaurants() {
    RestaurantModel restaurant1 = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantModel restaurant2 = new RestaurantModel("Restaurante 2", "Avenida Siempre Viva 742", false);
    repository.addRestaurant(restaurant1);
    repository.addRestaurant(restaurant2);
    List<RestaurantModel> restaurants = repository.getAllRestaurants();
    assertEquals(2, restaurants.size());
    assertTrue(restaurants.contains(restaurant1));
    assertTrue(restaurants.contains(restaurant2));
  }

  @Test
  @DisplayName("Test Remove Restaurant")
  void testRemoveRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    repository.removeRestaurant("Restaurante 1");
    assertNull(repository.getRestaurant("Restaurante 1"));
    verify(mockObserver).update("Restaurante eliminado: Restaurante 1");
  }

  @Test
  @DisplayName("Test Add Dish")
  void testAddDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    assertEquals(dish, repository.getDish("Plato 1"));
    verify(mockObserver).update("Nuevo plato agregado: Plato 1");
  }

  @Test
  @DisplayName("Test Get Dish")
  void testGetDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    assertEquals(dish, repository.getDish("Plato 1"));
  }

  @Test
  @DisplayName("Test Add Review To Dish")
  void testAddReviewToDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
    repository.addReviewToDish(review);
    assertNotNull(repository.getDish("Plato 1"));
    assertEquals(1, repository.getDish("Plato 1").getReviews().size());
    verify(mockObserver).update("Nueva review agregada al plato: Plato 1");
  }

  @Test
  @DisplayName("Test Add Review To Restaurant")
  void testAddReviewToRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    repository.addReviewToRestaurant(review);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertEquals(1, repository.getRestaurant("Restaurante 1").getReviews().size());
    verify(mockObserver).update("Nueva review agregada al restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Associate Menu To Restaurant")
  void testAssociateMenuToRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    repository.associateMenuToRestaurant("Restaurante 1", menu);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertEquals(menu, repository.getRestaurant("Restaurante 1").getMenu());
    verify(mockObserver).update("Menu asociado con éxito al restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    repository.associateMenuToRestaurant("Restaurante 1", menu);
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    repository.addDishToMenu("Restaurante 1", dish);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertNotNull(repository.getRestaurant("Restaurante 1").getMenu());
    assertEquals(1, repository.getRestaurant("Restaurante 1").getMenu().getDishes().size());
    verify(mockObserver).update("Plato agregado con éxito al menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Edit Dish In Menu")
  void testEditDishInMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    repository.associateMenuToRestaurant("Restaurante 1", menu);
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    repository.addDishToMenu("Restaurante 1", dish);
    DishModel updatedDish = new DishModel("Plato 1", "Nueva Descripción", 15.0);
    repository.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertNotNull(repository.getRestaurant("Restaurante 1").getMenu());
    assertEquals("Nueva Descripción", repository.getRestaurant("Restaurante 1").getMenu().getDishes().get(0).getDescription());
    assertEquals(15.0, repository.getRestaurant("Restaurante 1").getMenu().getDishes().get(0).getPrice());
    verify(mockObserver).update("Plato editado con éxito en el menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    repository.associateMenuToRestaurant("Restaurante 1", menu);
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    repository.addDish(dish);
    repository.addDishToMenu("Restaurante 1", dish);
    repository.removeDishFromMenu("Restaurante 1", "Plato 1");
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertNotNull(repository.getRestaurant("Restaurante 1").getMenu());
    assertTrue(repository.getRestaurant("Restaurante 1").getMenu().getDishes().isEmpty());
    verify(mockObserver).update("Plato eliminado con éxito del menú de restaurante: Restaurante 1");
  }

  @Test
  @DisplayName("Test Calculate Average Rating Restaurant")
  void testCalculateAverageRatingRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    repository.addRestaurant(restaurant);
    RestaurantReviewModel review1 = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    RestaurantReviewModel review2 = new RestaurantReviewModel("Cliente 2", 3.0, "Bueno", restaurant);
    repository.addReviewToRestaurant(review1);
    repository.addReviewToRestaurant(review2);
    assertNotNull(repository.getRestaurant("Restaurante 1"));
    assertEquals(4.0, repository.calculateAverageRatingRestaurant("Restaurante 1"));
  }
}