package org.example.services.menu;

import org.example.services.interfaces.ICommandParametrized;
import org.example.models.Menu;

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
