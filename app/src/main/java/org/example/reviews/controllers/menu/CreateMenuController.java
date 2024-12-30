package org.example.reviews.controllers.menu;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Menu;
import org.example.reviews.services.menus.MenuService;

public class CreateMenuController implements IController {
    private MenuService menuService;

    public CreateMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void execute() {
        System.out.println("---Creando menu---");
        Menu menu = menuService.createMenu();
        System.out.println(menu);
        System.out.println("---Menu creado con exito---");
    }
}
