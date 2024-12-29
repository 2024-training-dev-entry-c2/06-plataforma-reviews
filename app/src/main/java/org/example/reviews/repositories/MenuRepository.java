package org.example.reviews.repositories;

import org.example.reviews.models.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuRepository {
    private static MenuRepository INSTANCE;
    Map<Integer, Menu> menus;

    private MenuRepository(){
        this.menus = new HashMap<>();
    }

    public static synchronized MenuRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MenuRepository();
        }
            return INSTANCE;

    }

    public void addMenu(Integer restaurantId,Menu menu){
        menus.put(restaurantId, menu);
    }

    public Map<Integer, Menu> getMenus() {
        return menus;
    }

    public void setMenus(Map<Integer, Menu> menus) {
        this.menus = menus;
    }
}
