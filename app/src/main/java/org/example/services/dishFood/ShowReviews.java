package org.example.services.dishFood;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.models.ReviewDish;
import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class ShowReviews implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final DishRepository dishRepository = DishRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public ShowReviews(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        DishFood dishFood=new DishFood();
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            showDishFood(restaurant);
            Integer optionDish = validatorScanner.integerScanner("seleccione un plato para ver el review");
            dishFood= validate(restaurant,optionDish);
            dishRepository.showReview(dishFood);
            System.out.println("Review added successfully to: " + restaurantName);
        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        }
    }


    public DishFood validate(Restaurant restaurant, int optionDish){

        if (!restaurant.getMenu().getDishFoodList().isEmpty()) {

           return restaurant.getMenu().getDishFoodList().get(optionDish - 1);
        }
        //trampa
        DishFood dishFood = new DishFood("null", "null", 2000.0);
        dishFood.getReviewList().add(new ReviewDish("feo", 0.3F, 0.2F));
        return dishFood;
    }
    public void showDishFood(Restaurant restaurant){
        if (restaurant != null){
            menuRepository.showDishes(restaurant.getMenu());
        }
    }
}


