package org.example.services;

import org.example.models.DishModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.DataRepository;

public class DishService implements Observer {
  private final DataRepository repository;

  public DishService() {
    this.repository = DataRepository.getInstance();
    repository.addObserver(this);
  }

  public void createDish(String name, String description, double price) {
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
