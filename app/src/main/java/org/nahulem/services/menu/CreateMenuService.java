package org.nahulem.services.menu;

import org.nahulem.models.Menu;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

public class CreateMenuService implements ICommand<Menu> {
    private final Validator validator;
    private final DataRepository repository;
    private AddDishService addDishService;

    public CreateMenuService(AddDishService addDishService, Validator validator, DataRepository repository) {
        this.addDishService = addDishService;
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public Menu execute() {
        String name = validator.readString("Ingresa el nombre del menú: " + "\n");
        Menu menu = new Menu(name, null);

        repository.addMenu(menu);
        addDishesToMenu(menu);

        validator.printMessage("Menú completado con éxito con los platos asociados." + "\n");
        return menu;
    }

    private void addDishesToMenu(Menu menu) {
        Boolean continueAdding = true;
        while (continueAdding) {
            addDish(menu);
            continueAdding = validator.readBoolean("¿Desea agregar otro plato al menú? (S/N): ");
        }
    }

    private void addDish(Menu menu) {
        validator.printMessage("Agregar un nuevo plato al menú..." + "\n");
        addDishService = new AddDishService(menu, validator, repository);
        addDishService.execute();
    }
}
