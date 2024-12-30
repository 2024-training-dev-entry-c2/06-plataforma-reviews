package org.example.repositories;

import org.example.models.DishModel;
import org.example.models.DishReviewModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.Interface.observable.Observable;
import org.example.Interface.observable.Observer;
import org.example.models.RestaurantReviewModel;

import java.util.*;

public class DataRepository implements Observable {

  private static DataRepository instance;

  private final Map<String, RestaurantModel> restaurants;
  private final Map<String, DishModel> dishes;
  private final List<Observer> observers;

  private DataRepository() {
    this.restaurants = new HashMap<>();
    this.dishes = new HashMap<>();
    this.observers = new ArrayList<>();
  }

  public static DataRepository getInstance() {
    if (instance == null) {
      synchronized (DataRepository.class) {
        instance = new DataRepository();
      }
    }
    return instance;
  }

  public void clear() {
    restaurants.clear();
    dishes.clear();
    observers.clear();
  }

  public Double calculateAverageRatingRestaurant(String restaurant) {
    return restaurants.get(restaurant).getAverageRating();
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for (Observer observer : observers) {
      observer.update(message);
    }
  }

  public void addRestaurant(RestaurantModel restaurant) {
    Objects.requireNonNull(restaurant, "El restaurante no puede ser nulo.");
    if (restaurants.containsKey(restaurant.getName())) {
      throw new IllegalArgumentException("El restaurante ya existe: " + restaurant.getName());
    }
    restaurants.put(restaurant.getName(), restaurant);
    notifyObservers("Nuevo restaurante agregado: " + restaurant.getName());
  }

  public void updateRestaurant(RestaurantModel restaurant) {
    Objects.requireNonNull(restaurant, "El restaurante no puede ser nulo.");
    if (!restaurants.containsKey(restaurant.getName())) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurant.getName());
    }
    restaurants.put(restaurant.getName(), restaurant);
    notifyObservers("Restaurante actualizado: " + restaurant.getName());
  }

  public RestaurantModel getRestaurant(String name) {
    return restaurants.get(name);
  }

  public List<RestaurantModel> getAllRestaurants() {
    return new ArrayList<>(restaurants.values());
  }

  public void removeRestaurant(String name) {
    if (!restaurants.containsKey(name)) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + name);
    }
    restaurants.remove(name);
    notifyObservers("Restaurante eliminado: " + name);
  }

  public void addDish(DishModel dish) {
    Objects.requireNonNull(dish, "El plato no puede ser nulo.");
    if (dishes.containsKey(dish.getName())) {
      throw new IllegalArgumentException("EL plato ya existe: " + dish.getName());
    }
    dishes.put(dish.getName(), dish);
    notifyObservers("Nuevo plato agregado: " + dish.getName());
  }

  public DishModel getDish(String name) {
    return dishes.get(name);
  }

  public void addReviewToDish(DishReviewModel review) {
    DishModel dish = dishes.get(review.getDish().getName());
    if (dish == null) {
      throw new IllegalArgumentException("El plato no existe: " + review.getDish().getName());
    }
    review.getDish().addReview(review);
    dishes.put(review.getDish().getName(), review.getDish());
    notifyObservers("Nueva review agregada al plato: " + dish.getName());
  }

  public void addReviewToRestaurant(RestaurantReviewModel review) {
    RestaurantModel restaurant = restaurants.get(review.getRestaurant().getName());
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + review.getRestaurant().getName());
    }
    review.getRestaurant().addReview(review);
    restaurants.put(review.getRestaurant().getName(), review.getRestaurant());
    notifyObservers("Nueva review agregada al restaurante: " + restaurant.getName());
  }

  public void associateMenuToRestaurant(String restaurantName, MenuModel menu) {
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    restaurant.setMenu(menu);
    notifyObservers("Menu asociado con éxito al restaurante: " + restaurantName);
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null || dishes.get(dish.getName()) == null) {
      throw new IllegalArgumentException("Restaurante, menú o plato no encontrado: " + restaurantName);
    }
    restaurant.getMenu().addDish(dish);
    notifyObservers("Plato agregado con éxito al menú de restaurante: " + restaurantName);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    RestaurantModel restaurant = getRestaurantWithMenu(restaurantName);
    DishModel dish = findDishInMenu(restaurant.getMenu(), dishName);
    updateDishInMenu(restaurant.getMenu(), dish, updatedDish);
    notifyObservers("Plato editado con éxito en el menú de restaurante: " + restaurantName);
  }

  private RestaurantModel getRestaurantWithMenu(String restaurantName) {
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado: " + restaurantName);
    }
    return restaurant;
  }

  private DishModel findDishInMenu(MenuModel menu, String dishName) {
    return menu.getDishes().stream()
      .filter(dish -> dish.getName().equals(dishName))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Tarea terminada"));
  }

  private void updateDishInMenu(MenuModel menu, DishModel dish, DishModel updatedDish) {
    List<DishModel> dishes = menu.getDishes();
    int index = dishes.indexOf(dish);
    dishes.set(index, updatedDish);
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    RestaurantModel restaurant = getRestaurantWithMenu(restaurantName);
    DishModel dish = findDishInMenu(restaurant.getMenu(), dishName);
    removeDishFromMenu(restaurant.getMenu(), dish);
    notifyObservers("Plato eliminado con éxito del menú de restaurante: " + restaurantName);
  }

  private void removeDishFromMenu(MenuModel menu, DishModel dish) {
    menu.removeDish(dish);
  }
}