package org.example.services.menu;

import org.example.repositories.DataRepository;
import org.example.services.interfaces.ICommand;

public class SelectDishService implements ICommand<Boolean> {
    private final DataRepository repository;

    public SelectDishService(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean execute() {
        return null;
    }
}
