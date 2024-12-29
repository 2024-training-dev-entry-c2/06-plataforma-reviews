package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;

import java.util.Objects;

public class DeleteDishService implements ICommand<Boolean> {
    private final DataRepository repository;
    private final SelectDishService selectDishService;

    public DeleteDishService(DataRepository repository, SelectDishService selectDishService) {
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
