package org.example.reviews.repositories;

import org.example.reviews.models.Menu;

import java.util.LinkedList;
import java.util.List;

public class MenuRepository {
    private static MenuRepository INSTANCE;
    List<Menu> menus;

    private MenuRepository(){
        this.menus = new LinkedList<>();
    }

    public static synchronized MenuRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MenuRepository();
        }
            return INSTANCE;

    }

    public void addMenu(Menu menu){
        menus.add(menu);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
