package org.nahulem.utils;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.controllers.menu.AddDishController;
import org.nahulem.controllers.menu.DeleteDishController;
import org.nahulem.controllers.menu.UpdateDishController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.models.Menu;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.services.menu.DeleteDishService;
import org.nahulem.services.menu.SelectDishService;
import org.nahulem.services.menu.UpdateDishService;
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
    DeleteRestaurantService deleteRestaurant = new DeleteRestaurantService(repository, selectRestaurant);
    SelectDishService selectDish = new SelectDishService(validator, selectRestaurant);
    UpdateDishService updateDish = new UpdateDishService(repository, selectDish, validator);
    DeleteDishService deleteDish = new DeleteDishService(repository, selectDish);

    private Integer showMainMenu() {
        return validator.readInt(
                """
                        
                        ===============================================\
                        
                         BIENVENIDO A LA APLICACIÓN DE RESTAURANTES\
                        
                        ===============================================\
                        
                        1. Crear restaurante\
                        
                        2. Listar restaurantes\
                        
                        3. Actualizar restaurante\
                        
                        4. Eliminar restaurante\
                        
                        5. Agregar plato a un menú\
                        
                        6. Actualizar plato de un menú\
                        
                        7. Eliminar plato de un menú\
                        
                        10. Salir\
                        
                        ===============================================\
                        
                        Seleccione una opción:\s"""
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
        controllerMap.put(6, new UpdateDishController(updateDish));
        controllerMap.put(7, new DeleteDishController(deleteDish));

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
