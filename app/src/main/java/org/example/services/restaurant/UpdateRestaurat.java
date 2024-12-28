package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class UpdateRestaurat implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();


//    public UpdateRestaurat(IValidatorScanner validatorScanner) {
//        this.validatorScanner = validatorScanner;
//    }

    @Override
    public void execute() {
        System.out.println("hlaaaaaaaa");

    }
}
