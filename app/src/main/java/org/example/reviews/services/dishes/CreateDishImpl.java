package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

public class CreateDishImpl implements ICommand<Dish> {
    private DishRepository dishRepository;
    private ConsoleUtil console;

    public CreateDishImpl(ConsoleUtil console){
        this.console = console;
        this.dishRepository = DishRepository.getInstance();
    }
    @Override
    public Dish execute() {
        Integer id = console.readInt("Introduzca el id del plato: ");
        String name = console.readLine("Introduzca el nombre del plato: ");
        Double price = console.readDouble("Introduzca el precio del plato: ");
        Dish dish = new Dish(id, name, price);
        dishRepository.addDish(dish);
        return dish;
    }
}
