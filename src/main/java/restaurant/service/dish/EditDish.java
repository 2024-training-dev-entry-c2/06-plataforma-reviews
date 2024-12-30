package restaurant.service.dish;

import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DishUtils;
import restaurant.utils.RestaurantUtils;

import java.util.List;

public class EditDish implements ICommand<Void> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;
    private final RestaurantUtils restaurantUtils;
    private final DishUtils dishUtils;

    public EditDish(RestaurantRepository repository, ConsoleUtils console, RestaurantUtils restaurantUtils, DishUtils dishUtils) {
        this.repository = repository;
        this.console = console;
        this.restaurantUtils = restaurantUtils;
        this.dishUtils = dishUtils;
    }

    @Override
    public Void execute() {
        Menu menu = selectMenu();
        if (menu == null) return null;

        List<Dish> dishes = menu.getDishes();
        dishUtils.showDishes(dishes);

        int index = getDishIndex(dishes);
        if (index == -1) return null;

        Dish dish = dishes.get(index);

        if (!editDishDetails(dish)) return null;

        System.out.println("Plato editado: " + dish.getName());
        return null;
    }

    private Menu selectMenu() {
        Menu menu = restaurantUtils.selectMenu(repository, console);
        if (menu == null) {
            System.out.println("No se seleccionó un menú.");
        }
        return menu;
    }

    private int getDishIndex(List<Dish> dishes) {
        return dishUtils.getDishIndex(console, dishes, "Introduce el número del plato que deseas editar: ");
    }

    private boolean editDishDetails(Dish dish) {
        String newName = console.getString("Introduce el nuevo nombre del plato: ");
        if (isInvalidInput(newName, "El nombre del plato no puede estar vacío.")) return false;

        String newDescription = console.getString("Introduce la nueva descripción del plato: ");
        if (isInvalidInput(newDescription, "La descripción del plato no puede estar vacía.")) return false;

        double newPrice = console.getDouble("Introduce el nuevo precio del plato: ");
        if (newPrice <= 0) {
            System.out.println("El precio debe ser mayor que 0.");
            return false;
        }

        dish.setName(newName);
        dish.setDescription(newDescription);
        dish.setPrice(newPrice);
        return true;
    }

    private boolean isInvalidInput(String input, String errorMessage) {
        if (input.isEmpty()) {
            System.out.println(errorMessage);
            return true;
        }
        return false;
    }
}