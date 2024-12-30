package org.example.reviews.controllers.menu;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Menu;
import org.example.reviews.services.menus.MenuService;

import java.util.List;

public class FindMenusController implements IController {
    private MenuService menuService;

    public FindMenusController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void execute() {
        List<Menu> availableMenus = menuService.displayMenus();

        if (availableMenus.isEmpty()) {
            System.out.println("No hay menus disponibles");
            return;
        }
        availableMenus.forEach(System.out::println);
    }
}
