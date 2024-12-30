
  package org.example.repositories;

import org.example.models.DishModel;
import org.example.models.DishReviewModel;
import org.example.Interface.observable.Observable;
import org.example.Interface.observable.Observer;

import java.util.*;

public class DishRepository implements Observable {

  private static DishRepository instance;

  private final Map<String, DishModel> dishes;
  private final List<Observer> observers;

  private DishRepository() {
    this.dishes = new HashMap<>();
    this.observers = new ArrayList<>();
  }

  public static DishRepository getInstance() {
    if (instance == null) {
      synchronized (DishRepository.class) {
        instance = new DishRepository();
      }
    }
    return instance;
  }

  public void clear() {
    dishes.clear();
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

  public void addDish(DishModel dish) {
    Objects.requireNonNull(dish, "El plato no puede ser nulo.");
    if (dishes.containsKey(dish.getName())) {
      throw new IllegalArgumentException("El plato ya existe: " + dish.getName());
    }
    dishes.put(dish.getName(), dish);
    notifyObservers("Nuevo plato agregado: " + dish.getName());
  }

  public DishModel getDish(String name) {
    return dishes.get(name);
  }

  public void addReviewToDish(DishReviewModel review) {
    Objects.requireNonNull(review, "La review no puede ser nula.");
    DishModel dish = dishes.get(review.getDish().getName());
    if (dish == null) {
      throw new IllegalArgumentException("El plato no existe: " + review.getDish().getName());
    }
    review.getDish().addReview(review);
    dishes.put(review.getDish().getName(), review.getDish());
    notifyObservers("Nueva review agregada al plato: " + dish.getName());
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    Objects.requireNonNull(dish, "El plato no puede ser nulo.");
    notifyObservers("Plato agregado con éxito al menú de restaurante: " + restaurantName);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    Objects.requireNonNull(updatedDish, "El plato actualizado no puede ser nulo.");
    DishModel dish = dishes.get(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }
    dishes.put(dishName, updatedDish);
    notifyObservers("Plato editado con éxito en el menú de restaurante: " + restaurantName);
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    Objects.requireNonNull(dishName, "El nombre del plato no puede ser nulo.");
    DishModel dish = dishes.get(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }
    dishes.remove(dishName);
    notifyObservers("Plato eliminado con éxito del menú de restaurante: " + restaurantName);
  }
}