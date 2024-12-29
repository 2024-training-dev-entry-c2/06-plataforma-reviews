package org.nahulem.services.menu;

import org.nahulem.services.interfaces.ICommandParametrized;
import org.nahulem.models.Menu;

public class DeleteDishService implements ICommandParametrized<Boolean, String> {
    private final Menu menu;

    public DeleteDishService(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Boolean execute(String dishId) {
        menu.deleteDish(dishId);
        return true;
    }
}
