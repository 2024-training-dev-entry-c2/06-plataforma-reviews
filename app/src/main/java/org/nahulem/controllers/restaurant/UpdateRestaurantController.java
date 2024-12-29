package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.restaurant.UpdateRestaurantService;
import org.nahulem.utils.Validator;

public class UpdateRestaurantController implements ICommandController {
    private final UpdateRestaurantService updateRestaurantService;
    private final Validator validator;

    public UpdateRestaurantController(UpdateRestaurantService updateRestaurantService, Validator validator) {
        this.updateRestaurantService = updateRestaurantService;
        this.validator = validator;
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
