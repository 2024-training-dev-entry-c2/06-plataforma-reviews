package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.restaurant.UpdateRestaurantService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class UpdateRestaurantController implements ICommandController {
    private final UpdateRestaurantService updateRestaurantService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public UpdateRestaurantController(UpdateRestaurantService updateRestaurantService) {
        this.updateRestaurantService = updateRestaurantService;
    }


    @Override
    public void execute() {
        boolean updated = updateRestaurantService.execute();
        if (updated) {
            validator.printMessage("Restaurante actualizado exitosamente.");
            return;
        }
        validator.printMessage("No se pudo actualizar el restaurante.");

    }
}
