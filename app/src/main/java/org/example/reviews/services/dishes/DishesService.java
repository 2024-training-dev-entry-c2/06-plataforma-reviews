package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.services.interfaces.IDish;

import java.util.List;
import java.util.Map;

public class DishesService implements IDish {
    private CreateDishImpl createDishImpl;
    private FindDishesImpl findDishesImpl;
    private UpdateDishImpl updateDishImpl;

    public DishesService(CreateDishImpl createDishImpl, FindDishesImpl findDishesImpl, UpdateDishImpl updateDishImpl) {
        this.createDishImpl = createDishImpl;
        this.findDishesImpl = findDishesImpl;
        this.updateDishImpl = updateDishImpl;
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
