package org.example.reviews.services.menus;

import org.example.reviews.models.Menu;
import org.example.reviews.services.interfaces.IMenu;

import java.util.List;

public class MenuService implements IMenu {
    private CreateMenuImpl createMenuImpl;
    private FindMenusImpl findMenusImpl;

    public MenuService(CreateMenuImpl createMenuImpl, FindMenusImpl findMenusImpl) {
        this.createMenuImpl = createMenuImpl;
        this.findMenusImpl = findMenusImpl;

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
