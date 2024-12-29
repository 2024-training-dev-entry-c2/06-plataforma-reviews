package org.nahulem.utils;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.controllers.menu.AddDishController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.models.Menu;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.services.restaurant.CreateRestaurantService;
import org.nahulem.services.restaurant.DeleteRestaurantService;
import org.nahulem.services.restaurant.ListRestaurantService;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.services.restaurant.UpdateRestaurantService;
import org.nahulem.utils.interfaces.IMainMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements IMainMenu {
    DataRepository repository = DataRepository.getInstance();
    Validator validator = new Validator(new Scanner(System.in));
    Menu menu = new Menu();
    ListRestaurantService listRestaurant = new ListRestaurantService(repository);
    SelectRestaurantService selectRestaurant = new SelectRestaurantService(repository, validator);
    AddDishService addDishService = new AddDishService(validator, repository, selectRestaurant);
    CreateRestaurantService createRestaurant = new CreateRestaurantService(addDishService, repository, validator);
    UpdateRestaurantService updateRestaurant = new UpdateRestaurantService(repository, selectRestaurant, validator);
    DeleteRestaurantService deleteRestaurant = new DeleteRestaurantService(repository, selectRestaurant, validator);

    private Integer showMainMenu() {
        return validator.readInt(
                "\n===============================================" +
                        "\n BIENVENIDO A LA APLICACIÓN DE RESTAURANTES" +
                        "\n===============================================" +
                        "\n1. Crear restaurante" +
                        "\n2. Listar restaurantes" +
                        "\n3. Actualizar restaurante" +
                        "\n4. Eliminar restaurante" +
                        "\n5. Agregar plato a un menú" +
                        "\n6. Actualizar plato de un menú" +
                        "\n7. Eliminar plato de un menú" +
                        "\n10. Salir" +
                        "\n===============================================" +
                        "\nSeleccione una opción: "
        );
    }



    @Override
    public Integer execute() {
        Map<Integer, ICommandController> controllerMap = new HashMap<>();
        controllerMap.put(1, new CreateRestaurantController(createRestaurant));
        controllerMap.put(2, new ListRestaurantController(listRestaurant));
        controllerMap.put(3, new UpdateRestaurantController(updateRestaurant));
        controllerMap.put(4, new DeleteRestaurantController(deleteRestaurant));
        controllerMap.put(5, new AddDishController(addDishService));

        Integer option;

        do {
            option = showMainMenu();
            if (controllerMap.containsKey(option)) {
                controllerMap.get(option).execute();
            } else {
               validator.printMessage("La opción ingresada no es válida. Por favor, elija una opción válida.");
            }
        } while (option != 10);
        return option;
    }

}
