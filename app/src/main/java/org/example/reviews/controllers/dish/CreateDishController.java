package org.example.reviews.controllers.dish;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Dish;
import org.example.reviews.services.dishes.DishesService;

public class CreateDishController implements IController {
    private DishesService dishesService;

    public CreateDishController(DishesService dishesService){
        this.dishesService = dishesService;
    }
    @Override
    public void execute() {
        System.out.println("--Nuevo plato---");
        Dish dish = dishesService.createDish();
        System.out.println(dish);
        System.out.println("---Plato creado con exito---");
    }
}
