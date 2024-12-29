package org.example.controllers.menu;

import org.example.controllers.restaurant.RestaurantController;
import org.example.services.Menu.AddDishFood;
import org.example.services.Menu.RemoveDishFood;
import org.example.services.Menu.ShowDishFood;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class MenuController {
    public static IValidatorScanner validatorScanner;

    public MenuController(IValidatorScanner validatorScanner) {
        MenuController.validatorScanner = validatorScanner;
    }

    public void addDishFood(){
        ICommand command = new AddDishFood(validatorScanner);
        command.execute();
    }

    public void removeDishFood(){
        ICommand command = new RemoveDishFood(validatorScanner);
        command.execute();
    }
    public void ShowDishFood(){
        ICommand command = new ShowDishFood(validatorScanner);
        command.execute();
    }



}
