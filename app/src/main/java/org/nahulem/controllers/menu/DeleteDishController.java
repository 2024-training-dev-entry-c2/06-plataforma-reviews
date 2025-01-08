package org.nahulem.controllers.menu;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.menu.DeleteDishService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class DeleteDishController implements ICommandController {
    private final DeleteDishService deleteDishService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public DeleteDishController(DeleteDishService deleteDishService) {
        this.deleteDishService = deleteDishService;
    }

    @Override
    public void execute() {
        Boolean dish = deleteDishService.execute();
        if (!dish) {
            validator.printMessage("ERROR! No se pudo eliminar el plato.");
            return;
        }
        validator.printMessage("Plato eliminado exitosamente.");
    }
}
