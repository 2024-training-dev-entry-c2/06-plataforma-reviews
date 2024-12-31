package org.example.services.dish;

import org.example.models.Dish;
import org.example.repositories.DishRepository;
import org.example.services.interfaces.ICommand;

public class AddDishService implements ICommand<Dish> {

    private final DishRepository repository;
    private Dish dish;

    public AddDishService(DishRepository repository) {
        this.repository = repository;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public Dish execute() {
        if (dish == null) {
            throw new IllegalStateException("Dish data cannot be null");
        }
        repository.addDish(dish);
        return dish;
    }
}