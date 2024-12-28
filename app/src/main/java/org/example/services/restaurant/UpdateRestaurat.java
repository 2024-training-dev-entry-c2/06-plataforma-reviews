package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class UpdateRestaurat implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public UpdateRestaurat(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public Object execute() {
        AddRestaurant newRestaurant = new AddRestaurant(validatorScanner);
//        newRestaurant.execute();
//        repository.updateRestaurant(newRestaurant.execute())
        return null;
    }
}
