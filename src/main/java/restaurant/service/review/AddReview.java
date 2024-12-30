package restaurant.service.review;

import restaurant.Interface.IReview;
import restaurant.models.*;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;

import java.util.List;

public class AddReview implements ICommand<IReview> {
    private final RestaurantRepository restaurantRepository;
    private final ConsoleUtils consoleUtils;

    public AddReview(RestaurantRepository restaurantRepository, ConsoleUtils consoleUtils) {
        this.restaurantRepository = restaurantRepository;
        this.consoleUtils = consoleUtils;
    }

    @Override
    public IReview execute() {
        String reviewType = consoleUtils.getString("¿Deseas añadir una reseña a un restaurante o a un plato? (restaurant/dish): ");
        String restaurantName = consoleUtils.getString("Introduce el nombre del restaurante: ");
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);

        if (restaurant == null) {
            System.out.println("Restaurante no encontrado");
            return null;
        }

        if ("restaurant".equalsIgnoreCase(reviewType)) {
            return addRestaurantReview(restaurant);
        } else if ("dish".equalsIgnoreCase(reviewType)) {
            return addDishReview(restaurant);
        } else {
            System.out.println("Tipo de reseña no válido");
            return null;
        }
    }

   private IReview addRestaurantReview(Restaurant restaurant) {
    String comment = consoleUtils.getString("Introduce tu comentario: ");
    double qualification = consoleUtils.getDouble("Introduce tu calificación (0-5): ");
    IReview review = ReviewFactory.createRestaurantReview(comment, qualification, restaurant);
    restaurant.addReview(review);
    System.out.println("Reseña añadida al restaurante");
    return review;
}

private IReview addDishReview(Restaurant restaurant) {
    List<Dish> dishes = restaurant.getMenu().getDishes();
    if (dishes.isEmpty()) {
        System.out.println("No hay platos disponibles en el menú.");
        return null;
    }

    showDishesInMenu(dishes);

    String dishName = consoleUtils.getString("Introduce el nombre del plato: ");
    Dish dish = dishes.stream()
                      .filter(d -> d.getName().equalsIgnoreCase(dishName))
                      .findFirst()
                      .orElse(null);

    if (dish == null) {
        System.out.println("Plato no encontrado.");
        return null;
    }

    String comment = consoleUtils.getString("Introduce tu comentario: ");
    double qualification = consoleUtils.getDouble("Introduce tu calificación (0-10): ");
    IReview review = ReviewFactory.createDishReview(comment, qualification, dish);
    dish.addReview(review);
    System.out.println("Reseña añadida al plato");
    return review;
}

    private static void showDishesInMenu(List<Dish> dishes) {
        System.out.println("Platos disponibles:");
        for (Dish dish : dishes) {
            System.out.println("- " + dish.getName());
        }
    }
}