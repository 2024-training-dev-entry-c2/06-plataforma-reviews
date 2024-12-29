package org.nahulem.services.menu;

import org.nahulem.services.interfaces.ICommandParametrized;
import org.nahulem.models.Menu;
import org.nahulem.models.Dish;

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
