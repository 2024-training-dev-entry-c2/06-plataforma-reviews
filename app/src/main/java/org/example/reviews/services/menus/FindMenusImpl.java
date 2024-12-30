package org.example.reviews.services.menus;

import org.example.reviews.models.Menu;
import org.example.reviews.repositories.MenuRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.util.List;

public class FindMenusImpl implements ICommand<List<Menu>> {

    private MenuRepository menuRepository;
    private ConsoleUtil console;

    public FindMenusImpl(ConsoleUtil console){
        this.console = console;
        this.menuRepository = MenuRepository.getInstance();
    }
    @Override
    public List<Menu> execute() {
        return menuRepository.getMenusByRestaurantId(console.readInt("Ingrese el id del restaurante: "));
    }
}
