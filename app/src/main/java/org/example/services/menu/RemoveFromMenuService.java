package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.services.interfaces.ICommand;

public class RemoveFromMenuService implements ICommand<Void> {
    private final MenuRepository menuRepository;
    private Restaurant restaurant;
    private Dish dish;

    public RemoveFromMenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public Void execute() {
        if (restaurant == null || dish == null) {
            throw new IllegalStateException("El restaurante y el plato no pueden ser nulos");
        }
        menuRepository.removeDishFromMenu(restaurant, dish);
        return null;
    }
}