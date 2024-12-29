package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

import java.util.ArrayList;

public class AddDishService implements ICommand<Menu> {
    private final Validator validator;
    private final Menu menu;
    private final DataRepository repository;

    public AddDishService(Menu menu, Validator validator, DataRepository repository) {
        this.menu = menu;
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public Menu execute() {
        String name = validator.readString("Ingrese el nombre del plato: ");
        String description = validator.readString("Ingrese la descripción del plato: ");
        Float price = validator.readFloat("Ingrese el precio del plato: ");

        Dish dish = new Dish(name, description, price, new ArrayList<>());

        repository.addDish(dish);

        menu.addDish(dish);
        return menu;
    }
}
