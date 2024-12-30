package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;

public class AddDishService implements ICommand<Menu> {
    private final Validator validator;
    private final DataRepository repository;
    private final SelectRestaurantService selectRestaurantService;

    public AddDishService(Validator validator, DataRepository repository, SelectRestaurantService selectRestaurantService) {
        this.validator = validator;
        this.repository = repository;
        this.selectRestaurantService = selectRestaurantService;
    }

    @Override
    public Menu execute() {
        validator.printMessage("\n---Creando menu para el restaurante---\n");
        Restaurant restaurant = selectRestaurantService.execute();


        if (restaurant == null) {
            validator.printMessage("No se ha seleccionado ningún restaurante.");
            return null;
        }

        Menu menu = restaurant.getMenu();

        if (menu == null) {
            menu = createMenu();
            restaurant.setMenu(menu);
            repository.addMenu(menu);
        }

        addDishesToMenu(menu);
        return menu;
    }

    private Menu createMenu() {
        validator.printMessage("\n---Creando un nuevo menú---\n");
        String name = validator.readString("Ingresa el nombre del menú: \n");
        return new Menu(name, new ArrayList<>());
    }

    private void addDishesToMenu(Menu menu) {
        boolean continueAdding = true;
        while (continueAdding) {
            addDish(menu);
            continueAdding = validator.readBoolean("¿Desea agregar otro plato al menú? (S/N): \n");
        }
        validator.printMessage("Menú completado con éxito con los platos asociados.");
    }

    private void addDish(Menu menu) {
        validator.printMessage("\n---Agregando un nuevo plato---\n");
        String name = validator.readString("Ingrese el nombre del plato: \n");
        String description = validator.readString("Ingrese la descripción del plato: \n");
        Float price = validator.readFloat("Ingrese el precio del plato: \n");

        Dish newDish = new Dish(name, description, price);
        repository.addDish(newDish);
        menu.addDish(newDish);
        validator.printMessage("Plato agregado exitosamente al menú.");
    }
}
