package org.nahulem.utils;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.controllers.menu.AddDishController;
import org.nahulem.controllers.menu.DeleteDishController;
import org.nahulem.controllers.menu.UpdateDishController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.controllers.review.CreateDishReviewController;
import org.nahulem.controllers.review.CreateRestaurantReviewController;
import org.nahulem.controllers.review.ShowDishReviewController;
import org.nahulem.controllers.review.ShowRestaurantReviewController;
import org.nahulem.utils.interfaces.IMainMenu;

import java.util.HashMap;
import java.util.Map;

public class MainMenu implements IMainMenu<Integer> {
    private final Validator validator;
    private final CreateRestaurantController createRestaurant;
    private final UpdateRestaurantController updateRestaurant;
    private final DeleteRestaurantController deleteRestaurant;
    private final ListRestaurantController listRestaurant;
    private final AddDishController addDish;
    private final UpdateDishController updateDish;
    private final DeleteDishController deleteDish;
    private final CreateDishReviewController createDishReview;
    private final ShowDishReviewController showDishReview;
    private final CreateRestaurantReviewController createRestaurantReview;
    private final ShowRestaurantReviewController showRestaurantReview;

    public MainMenu(AddDishController addDish, Validator validator, CreateRestaurantController createRestaurant, UpdateRestaurantController updateRestaurant, DeleteRestaurantController deleteRestaurant, ListRestaurantController listRestaurant, UpdateDishController updateDish, DeleteDishController deleteDish, CreateDishReviewController createDishReview, ShowDishReviewController showDishReview, CreateRestaurantReviewController createRestaurantReview, ShowRestaurantReviewController showRestaurantReview) {
        this.addDish = addDish;
        this.validator = validator;
        this.createRestaurant = createRestaurant;
        this.updateRestaurant = updateRestaurant;
        this.deleteRestaurant = deleteRestaurant;
        this.listRestaurant = listRestaurant;
        this.updateDish = updateDish;
        this.deleteDish = deleteDish;
        this.createDishReview = createDishReview;
        this.showDishReview = showDishReview;
        this.createRestaurantReview = createRestaurantReview;
        this.showRestaurantReview = showRestaurantReview;
    }

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
                        
                        8. Agregar reseña a un plato\
                        
                        9. Listar reseñas de un plato\
                        
                        10. Agregar reseña a un restaurante\
                        
                        11. Listar reseñas de un restaurante\
                        
                        12. Salir\
                        
                        ===============================================\
                        
                        Seleccione una opción:\s"""
        );
    }


    @Override
    public Integer execute() {
        Map<Integer, ICommandController> controllerMap = new HashMap<>();
        controllerMap.put(1, createRestaurant);
        controllerMap.put(2, listRestaurant);
        controllerMap.put(3, updateRestaurant);
        controllerMap.put(4, deleteRestaurant);
        controllerMap.put(5, addDish);
        controllerMap.put(6, updateDish);
        controllerMap.put(7, deleteDish);
        controllerMap.put(8, createDishReview);
        controllerMap.put(9, showDishReview);
        controllerMap.put(10, createRestaurantReview);
        controllerMap.put(11, showRestaurantReview);

        Integer option;

        while (true) {
            option = showMainMenu();

            if (option == 12) {
                validator.printMessage("Gracias por usar nuestra aplicación. ¡Hasta pronto!");
                break;
            }

            if (controllerMap.containsKey(option)) {
                controllerMap.get(option).execute();
            } else {
                validator.printMessage("La opción ingresada no es válida. Por favor, elija una opción válida.");
            }
        }
        return option;
    }

}