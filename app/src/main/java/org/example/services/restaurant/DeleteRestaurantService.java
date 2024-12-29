package org.example.services.restaurant;


import org.example.repositories.DataRepository;
import org.example.services.interfaces.ICommand;

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
