
package org.example.services;

import org.example.models.DishModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.DishRepository;

public class DishService implements Observer {
  private final DishRepository repository;

  public DishService() {
    this.repository = DishRepository.getInstance();
    repository.addObserver(this);
  }

  public void createDish(String name, String description, double price) {
    if (name == null) {
      throw new IllegalArgumentException("El nombre del plato no puede ser nulo.");
    }
    if (description == null) {
      throw new IllegalArgumentException("La descripción del plato no puede ser nula.");
    }
    if (price < 0) {
      throw new IllegalArgumentException("El precio del plato no puede ser negativo.");
    }
    DishModel dish = new DishModel(name, description, price);
    repository.addDish(dish);
  }

  public double getAverageRatingOfDish(String dishName) {
    DishModel dish = repository.getDish(dishName);
    if (dish != null) {
      return dish.getAverageRating();
    } else {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }
  }

  @Override
  public void update(String message) {
    if (message.toLowerCase().contains("plato")) {
      System.out.println("Servicio de platos revisado: " + message);
    }
  }
}