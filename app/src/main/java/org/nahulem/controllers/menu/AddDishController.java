package org.nahulem.controllers.menu;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.Menu;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class AddDishController implements ICommandController {
    private final Validator validator = new Validator(new Scanner(System.in));
    private final AddDishService addDishService;

    public AddDishController(Validator mockValidator, AddDishService addDishService) {
        this.addDishService = addDishService;
    }

    @Override
    public void execute() {
        Menu menu = addDishService.execute();

        if (menu == null) {
            validator.printMessage("No se pudo agregar el platillo al menú.");
            return;
        }
        validator.printMessage("Platillo agregado exitosamente al menú: " + menu.toString());

    }
}
