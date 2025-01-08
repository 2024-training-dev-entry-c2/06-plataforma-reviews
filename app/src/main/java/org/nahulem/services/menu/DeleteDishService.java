package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;

import java.util.Objects;

public class DeleteDishService implements ICommand<Boolean> {
    private final RestaurantRepository repository;
    private final SelectDishService selectDishService;

    public DeleteDishService(RestaurantRepository repository, SelectDishService selectDishService) {
        this.repository = repository;
        this.selectDishService = selectDishService;
    }

    @Override
    public Boolean execute() {
        Dish dish = selectDishService.execute();

        if (dish == null) {
            return false;
        }

        return repository.getAllRestaurants().values().stream()
                .map(Restaurant::getMenu)
                .filter(Objects::nonNull)
                .anyMatch(menu -> menu.getDishes().remove(dish));
    }
}
