package org.example.services.restaurant;

import org.example.models.User;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddReviewRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddReviewRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }
    public void addReview(String nameRestaurant, User user,String comment,Float... calificacioness){
        repository.addReview(nameRestaurant,user,comment,calificacioness);
    }

    @Override
    public Object execute() {
        repository.displayRestaurants();
        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
        String nameRestaurant = repository.getRestaurantNameByIndex(optionRestaurant);
        User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
        String comment =validatorScanner.stringScanner("Ingrese su comentario");
        Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
        Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
        addReview(nameRestaurant,user,comment,placeRating,menuRating);

        return null;
    }
}
