package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.services.interfaces.ICommand;

import java.util.LinkedList;

public class AddDishToMenuService implements ICommand<Void> {
    private final MenuRepository menuRepository;
    private Restaurant restaurant;
    private Dish dish;

    public AddDishToMenuService(MenuRepository menuRepository) {
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
        validateInputs();
        Menu menu = getOrCreateMenu();
        addDishToMenu(menu);
        return null;
    }

    private void validateInputs() {
        if (restaurant == null || dish == null) {
            throw new IllegalStateException("El restaurante y el plato no pueden ser nulos");
        }
    }

    private Menu getOrCreateMenu() {
        Menu menu = menuRepository.getMenuByRestaurant(restaurant);
        if (menu == null) {
            menu = new Menu(restaurant, new LinkedList<>());
            restaurant.setMenu(menu);
        }
        return menu;
    }

    private void addDishToMenu(Menu menu) {
        menuRepository.addDishToMenu(restaurant, dish);
    }
}