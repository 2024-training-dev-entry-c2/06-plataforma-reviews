package org.example.reviews.repositories;

import org.example.reviews.models.Menu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MenuRepository {
    private static MenuRepository INSTANCE;
    private Map<Integer, List<Menu>> menus;

    private MenuRepository(){
        this.menus = new HashMap<>();
    }

    public static synchronized MenuRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MenuRepository();
        }
            return INSTANCE;

    }

    public void addMenu(Integer restaurantId, Menu menu){
        if (!menus.containsKey(restaurantId)) {
            menus.put(restaurantId, new LinkedList<>());
        }
        menus.get(restaurantId).add(menu);
    }

    public List<Menu> getMenusByRestaurantId(Integer restaurantId) {
        return menus.getOrDefault(restaurantId, new LinkedList<>());
    }

    public Map<Integer, List<Menu>> getMenus() {
        return menus;
    }

    public void setMenus(Map<Integer, List<Menu>> menus) {
        this.menus = menus;
    }
}
