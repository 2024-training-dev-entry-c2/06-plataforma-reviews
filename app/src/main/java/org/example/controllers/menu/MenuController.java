package org.example.controllers.menu;

import org.example.controllers.restaurant.RestaurantController;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.Menu.AddDishFood;
import org.example.services.Menu.RemoveDishFood;
import org.example.services.Menu.ShowDishFood;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class MenuController {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    public final IValidatorScanner validatorScanner;

    public MenuController(RestaurantRepository restaurantRepository, MenuRepository menuRepository, IValidatorScanner validatorScanner) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.validatorScanner = validatorScanner;
    }

    public void addDishFood(){
        ICommand command = new AddDishFood(restaurantRepository,menuRepository,validatorScanner);
        command.execute();
    }

    public void removeDishFood(){
        ICommand command = new RemoveDishFood(restaurantRepository,menuRepository,validatorScanner);
        command.execute();
    }
    public void ShowDishFood(){
        ICommand command = new ShowDishFood(restaurantRepository,menuRepository,validatorScanner);
        command.execute();
    }




}
