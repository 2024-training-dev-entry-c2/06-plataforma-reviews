package org.example.reviews.controllers.dish;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Dish;
import org.example.reviews.services.dishes.DishesService;

public class UpdateDishController implements IController {
    private DishesService dishesService;

    public UpdateDishController(DishesService dishesService){
        this.dishesService = dishesService;
    }

    @Override
    public void execute() {
        System.out.println("---Actualizando plato---");
        Dish dish = dishesService.updateDish();
        System.out.println(dish);
        System.out.println("---Plato actualizado con exito---");
    }
}
