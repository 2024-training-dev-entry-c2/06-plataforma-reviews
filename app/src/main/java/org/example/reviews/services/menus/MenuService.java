package org.example.reviews.services.menus;

import org.example.reviews.models.Menu;
import org.example.reviews.services.interfaces.IMenu;
import org.example.reviews.utils.ConsoleUtil;

import java.util.List;

public class MenuService implements IMenu {
    CreateMenuImpl createMenuImpl;
    FindMenusImpl findMenusImpl;

    public MenuService(ConsoleUtil console) {
        this.createMenuImpl = new CreateMenuImpl(console);
        this.findMenusImpl = new FindMenusImpl(console);

    }

    @Override
    public Menu createMenu() {
        return createMenuImpl.execute();
    }

    @Override
    public List<Menu> displayMenus() {
        return findMenusImpl.execute();
    }
}
