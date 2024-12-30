package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

public class UpdateDishImpl implements ICommand<Dish> {
    private DishRepository dishRepository;
    private ConsoleUtil console;

    public UpdateDishImpl(ConsoleUtil console){
        this.console = console;
        this.dishRepository = DishRepository.getInstance();
    }
    @Override
    public Dish execute() {
        Dish dish = dishRepository.getDishById(console.readInt("Introduzca el id del plato a actualizar:"));
        String name = console.readLine("Introduzca el nuevo nombre del plato: ");
        Double price = console.readDouble("Introduzca el nuevo precio del plato: ");
        dish.setName(name);
        dish.setPrice(price);
        dishRepository.addDish(dish);
        return dish;
    }
}
