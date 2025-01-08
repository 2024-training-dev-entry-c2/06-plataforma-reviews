package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.restaurant.DeleteRestaurantService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class DeleteRestaurantController implements ICommandController {
    private final DeleteRestaurantService deleteRestaurantService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public DeleteRestaurantController(DeleteRestaurantService deleteRestaurantService) {
        this.deleteRestaurantService = deleteRestaurantService;
    }

    @Override
    public void execute() {
        Boolean restaurant = deleteRestaurantService.execute();
        if (!restaurant) {
            validator.printMessage("ERROR! No se pudo eliminar el restaurante.");
            return;
        }
        validator.printMessage("Restaurante eliminado exitosamente.");
    }
}
