package org.example.services.menu;

import org.example.services.interfaces.ICommandParametrized;
import org.example.models.Menu;
import org.example.models.Dish;

public class UpdateDishService implements ICommandParametrized<Boolean, Dish> {
    private final Menu menu;

    public UpdateDishService(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Boolean execute(Dish updatedDish) {
        menu.updateDish(updatedDish);
        return true;
    }
}
