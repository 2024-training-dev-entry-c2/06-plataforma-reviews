package org.nahulem.services.menu;

import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;

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
