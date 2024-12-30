package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.services.interfaces.IDish;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class DishesService implements IDish {
    CreateDishImpl createDishImpl;
    FindDishesImpl findDishesImpl;
    UpdateDishImpl updateDishImpl;

    public DishesService(ConsoleUtil console) {
        this.createDishImpl = new CreateDishImpl(console);
        this.findDishesImpl = new FindDishesImpl(console);
        this.updateDishImpl = new UpdateDishImpl(console);
    }

    @Override
    public Dish createDish() {
        return createDishImpl.execute();
    }

    @Override
    public Map<Integer, Dish> getDishes() {
        return findDishesImpl.execute();
    }

    @Override
    public Dish updateDish() {
        return updateDishImpl.execute();
    }
}
