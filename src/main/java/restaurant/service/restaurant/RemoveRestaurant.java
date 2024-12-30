package restaurant.service.restaurant;

import restaurant.models.Restaurant;
import restaurant.observer.IObserver;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;

import java.util.List;

public class RemoveRestaurant implements ICommand<Restaurant> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;
    private final RestaurantUtils restaurantUtils = new RestaurantUtils();

    public RemoveRestaurant(RestaurantRepository repository, ConsoleUtils console) {
        this.repository = repository;
        this.console = console;
    }

    @Override
    public Restaurant execute() {
        List<Restaurant> restaurants = repository.getRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes registrados.");
            return null;
        }
        restaurantUtils.showRestaurants(restaurants);

        selectRestaurant(restaurants);

        return null;
    }

    public void selectRestaurant(List<Restaurant> restaurants) {
        int index = console.getInteger("Introduce el número del restaurante que deseas eliminar: ") - 1;
        if (index >= 0 && index < restaurants.size()) {
            Restaurant restaurant = restaurants.get(index);
            removeAllObservers(restaurant);
            repository.removeRestaurant(restaurant);
            System.out.println("Restaurante " + restaurant.getName() + " eliminado.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private void removeAllObservers(Restaurant restaurant) {
        List<IObserver> observers = restaurant.getObservers();
        for (IObserver observer : observers) {
            observer.update("Restaurant removed");
            restaurant.removeObserver(observer);
        }
    }
}