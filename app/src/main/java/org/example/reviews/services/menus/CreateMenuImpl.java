package org.example.reviews.services.menus;

import org.example.reviews.models.Dish;
import org.example.reviews.models.Menu;
import org.example.reviews.models.Restaurant;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.repositories.MenuRepository;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CreateMenuImpl implements ICommand<Menu> {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private ConsoleUtil console;

    public CreateMenuImpl(ConsoleUtil console){
        this.dishRepository = DishRepository.getInstance();
        this.menuRepository = MenuRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.console = console;

    }

    @Override
    public Menu execute() {
        if (!isDataAvailable()) {
            throw new RuntimeException("Para crear un menu se necesita al menos un restaurante y un plato");
        }
        Menu menu = captureMenuData();
        menuRepository.addMenu(menu.getRestaurant().getId(), menu);
        return menu;
    }

    private boolean isDataAvailable() {
        return !dishRepository.getDishes().isEmpty() && !restaurantRepository.getRestaurants().isEmpty();
    }

    public Menu captureMenuData(){
        Integer id = console.readInt("Introduzca el id del menu: ");
        printDishes();
        List<Dish> selectedDishes = captureDishes();
        String menuName = console.readLine("Introduzca el nombre del menu: ");
        Restaurant restaurant = restaurantRepository.findRestaurantById(console.readInt("Asigne el id del restaurante al cual va dirigido el menu: "));
        Menu menu = new Menu(id, menuName, restaurant);
        menu.setDishes(selectedDishes);
        restaurant.addMenu(menu);

        return menu;
    }

    public void printDishes(){
        console.writeLine("Los platos disponibles son: ");
        Map<Integer, Dish> dishes = dishRepository.getDishes();
        for (Dish dish : dishes.values()) {
            System.out.println(dish.getId() + " - " + dish.getName());
        }
    }

    public List<Dish> captureDishes(){
        List<Dish> selectedDish = new LinkedList<>();

        dishRepository.getDishes().forEach((id, dish) -> {
            System.out.println("Desea agregar el plato: "+dish.getName()+" ?");
            if(console.readBooleanYesOrNo("Si/No")) {
                selectedDish.add(dish);
            }
        });

        return selectedDish;
    }
}
