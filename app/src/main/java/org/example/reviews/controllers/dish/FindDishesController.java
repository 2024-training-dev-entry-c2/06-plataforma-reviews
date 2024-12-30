package org.example.reviews.controllers.dish;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Dish;
import org.example.reviews.services.dishes.DishesService;

import java.util.Map;

public class FindDishesController implements IController {
    private DishesService dishesService;

    public FindDishesController(DishesService dishesService){
        this.dishesService = dishesService;
    }
    @Override
    public void execute() {
        System.out.println("---Platos disponibles---");
        Map<Integer, Dish> dishes = dishesService.getDishes();
        if (dishes.isEmpty()) {
            System.out.println("No hay platos disponibles");
            return;
        }
        dishes.forEach((id, dish) -> System.out.println(dish));

    }
}
