package org.nahulem.controllers.menu;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.Restaurant;
import org.nahulem.services.menu.UpdateDishService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class UpdateDishController implements ICommandController {
    private final UpdateDishService updateDishService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public UpdateDishController(UpdateDishService updateDishService) {
        this.updateDishService = updateDishService;
    }

    @Override
    public void execute() {
        Restaurant restaurant = updateDishService.execute();
        if (restaurant != null) {
            validator.printMessage("Plato actualizado exitosamente.");
            validator.printMessage(restaurant.toString());
            return;
        }
        validator.printMessage("No se pudo actualizar el plato.");
    }
}

