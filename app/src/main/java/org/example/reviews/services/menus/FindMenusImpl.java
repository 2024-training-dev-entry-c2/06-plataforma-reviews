package org.example.reviews.services.menus;

import org.example.reviews.models.Menu;
import org.example.reviews.repositories.MenuRepository;
import org.example.reviews.services.interfaces.ICommand;

import java.util.List;

public class FindMenusImpl implements ICommand<List<Menu>> {

    private MenuRepository menuRepository;

    public FindMenusImpl(){
        this.menuRepository = MenuRepository.getInstance();
    }
    @Override
    public List<Menu> execute() {
        return menuRepository.getMenus().values().stream().toList();
    }
}
