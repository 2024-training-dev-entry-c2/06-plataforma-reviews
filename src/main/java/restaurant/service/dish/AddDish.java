// src/main/java/restaurant/service/dish/AddDish.java
package restaurant.service.dish;

import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;

public class AddDish implements ICommand<Void> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;
    private final RestaurantUtils restaurantUtils;

    public AddDish(RestaurantRepository repository, ConsoleUtils console, RestaurantUtils restaurantUtils) {
        this.repository = repository;
        this.console = console;
        this.restaurantUtils = restaurantUtils;
    }

    @Override
    public Void execute() {

        Menu menu = restaurantUtils.selectMenu(repository, console);
        if (menu != null) {
            String name = console.getString("Introduce el nombre del plato: ");
            if (name.isEmpty()) {
                System.out.println("El nombre del plato no puede estar vacío.");
                return null;
            }

            String description = console.getString("Introduce la descripción del plato: ");
            if (description.isEmpty()) {
                System.out.println("La descripción del plato no puede estar vacía.");
                return null;
            }

            double price = console.getDouble("Introduce el precio del plato: ");
            if (price <= 0) {
                System.out.println("El precio debe ser mayor que 0.");
                return null;
            }
            Dish dish = new Dish(name, description, price);
            menu.addDish(dish);
            System.out.println("Plato añadido: " + name);
        }
        return null;
    }
}