package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class FindDishesImpl implements ICommand<Map<Integer, Dish>> {

    private DishRepository dishRepository;
    private ConsoleUtil console;

    public FindDishesImpl(ConsoleUtil console){
        this.console = console;
        this.dishRepository = DishRepository.getInstance();
    }
    @Override
    public Map<Integer, Dish> execute() {
        return dishRepository.getDishes();
    }
}
