package restaurant.service.dish;

import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DishUtils;
import restaurant.utils.RestaurantUtils;

import java.util.List;

public class RemoveDish implements ICommand<Void> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;
    private final RestaurantUtils restaurantUtils;
    private final DishUtils dishUtils ;

    public RemoveDish(RestaurantRepository repository, ConsoleUtils console, RestaurantUtils restaurantUtils, DishUtils dishUtils) {
        this.repository = repository;
        this.console = console;
        this.restaurantUtils = restaurantUtils;
        this.dishUtils = dishUtils;
    }


    @Override
    public Void execute() {
        Menu menu = restaurantUtils.selectMenu(repository, console);
        if (menu == null) {
            System.out.println("No menu selected.");
            return null;
        }

        List<Dish> dishes = menu.getDishes();
        dishUtils.showDishes(dishes);

        int index = dishUtils.getDishIndex(console, dishes, "Introduce el número del plato que deseas eliminar: ");
        if (index < 0 || index >= dishes.size()) {
            System.out.println("Invalid dish index.");
            return null;
        }

        Dish dish = dishes.get(index);
        menu.removeDish(dish);

        Restaurant restaurant = restaurantUtils.getRestaurantByMenu(repository, menu);
        repository.updateRestaurant(restaurant);

        System.out.println("Plato eliminado: " + dish.getName());
        return null;
    }
}