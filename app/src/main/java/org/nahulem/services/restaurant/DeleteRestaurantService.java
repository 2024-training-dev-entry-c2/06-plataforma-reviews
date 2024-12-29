package org.nahulem.services.restaurant;


import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;

public class DeleteRestaurantService implements ICommand<Boolean> {
    private final DataRepository repository;

    public DeleteRestaurantService(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean execute() {
        return null;
    }
}
