package org.nahulem.utils;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.models.Menu;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.services.menu.CreateMenuService;
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
    AddDishService addDishService = new AddDishService(menu, validator, repository);
    CreateMenuService createMenuService = new CreateMenuService( addDishService, validator, repository);
    CreateRestaurantService createRestaurant = new CreateRestaurantService(createMenuService, repository, validator);
    ListRestaurantService listRestaurant = new ListRestaurantService(repository);
    SelectRestaurantService selectRestaurant = new SelectRestaurantService(repository, validator);
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
                        "\n5. Salir" +
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
        controllerMap.put(5, () -> System.out.println("Gracias por usar la aplicación de restaurantes."));

        Integer option;

        do {
            option = showMainMenu();
            if (controllerMap.containsKey(option)) {
                controllerMap.get(option).execute();
            } else {
                System.out.println("La opción ingresada no es válida. Por favor, elija una opción válida.");
            }
        } while (option != 5);
        return option;
    }

}
