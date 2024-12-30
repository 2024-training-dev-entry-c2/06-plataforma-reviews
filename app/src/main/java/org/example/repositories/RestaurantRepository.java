package org.example.repositories;

import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.Interface.observable.Observable;
import org.example.Interface.observable.Observer;

import java.util.*;

public class RestaurantRepository implements Observable {

  private static RestaurantRepository instance;

  private final Map<String, RestaurantModel> restaurants;
  private final List<Observer> observers;

  private RestaurantRepository() {
    this.restaurants = new HashMap<>();
    this.observers = new ArrayList<>();
  }

  public static RestaurantRepository getInstance() {
    if (instance == null) {
      synchronized (RestaurantRepository.class) {
        instance = new RestaurantRepository();
      }
    }
    return instance;
  }

  public void clear() {
    restaurants.clear();
    observers.clear();
  }

  @Override
  public void addObserver(Observer observer) {
    Objects.requireNonNull(observer, "El observador no puede ser nulo.");
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    Objects.requireNonNull(observer, "El observador no puede ser nulo.");
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    Objects.requireNonNull(message, "El mensaje no puede ser nulo.");
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

  public void addReviewToRestaurant(RestaurantReviewModel review) {
    Objects.requireNonNull(review, "La review no puede ser nula.");
    RestaurantModel restaurant = restaurants.get(review.getRestaurant().getName());
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + review.getRestaurant().getName());
    }
    review.getRestaurant().addReview(review);
    restaurants.put(review.getRestaurant().getName(), review.getRestaurant());
    notifyObservers("Nueva review agregada al restaurante: " + restaurant.getName());
  }

  public void associateMenuToRestaurant(String restaurantName, MenuModel menu) {
    Objects.requireNonNull(menu, "El menú no puede ser nulo.");
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    restaurant.setMenu(menu);
    notifyObservers("Menu asociado con éxito al restaurante: " + restaurantName);
  }

  public Double calculateAverageRatingRestaurant(String restaurant) {
    return restaurants.get(restaurant).getAverageRating();
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    Objects.requireNonNull(dish, "El plato no puede ser nulo.");
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado: " + restaurantName);
    }
    restaurant.getMenu().addDish(dish);
    notifyObservers("Plato agregado con éxito al menú de restaurante: " + restaurantName);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    Objects.requireNonNull(updatedDish, "El plato actualizado no puede ser nulo.");
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado: " + restaurantName);
    }
    MenuModel menu = restaurant.getMenu();
    List<DishModel> dishes = menu.getDishes();
    for (int i = 0; i < dishes.size(); i++) {
      if (dishes.get(i).getName().equalsIgnoreCase(dishName)) {
        dishes.set(i, updatedDish);
        notifyObservers("Plato editado con éxito en el menú de restaurante: " + restaurantName);
        return;
      }
    }
    throw new IllegalArgumentException("Plato no encontrado en el menú: " + dishName);
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    Objects.requireNonNull(dishName, "El nombre del plato no puede ser nulo.");
    RestaurantModel restaurant = restaurants.get(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado: " + restaurantName);
    }
    DishModel dish = restaurant.getMenu().getDishes().stream()
      .filter(d -> d.getName().equals(dishName))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado en el menú: " + dishName));
    restaurant.getMenu().removeDish(dish);
    notifyObservers("Plato eliminado con éxito del menú de restaurante: " + restaurantName);
  }
}