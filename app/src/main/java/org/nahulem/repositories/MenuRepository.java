package org.nahulem.repositories;

import org.nahulem.models.Dish;
import org.nahulem.models.DishReview;
import org.nahulem.models.Menu;
import org.nahulem.models.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRepository {
    private static MenuRepository instance;
    private Map<Integer, Menu> menus;
    private Map<Integer, Dish> dishes;

    private MenuRepository() {
        this.menus = new HashMap<>();
        this.dishes = new HashMap<>();
        initialize();
    }

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public void clear() {
        dishes.clear();
        menus.clear();
    }

    public void addMenu(Menu menu) {
        menus.put(menu.getMenuId(), menu);
    }

    public Menu getMenu(Integer id) {
        return menus.get(id);
    }

    public Map<Integer, Menu> getAllMenus() {
        return menus;
    }

    public Boolean deleteMenu(Integer id) {
        return menus.remove(id) != null;
    }

    public Boolean updateMenu(Menu updatedMenu) {
        Integer menuId = updatedMenu.getMenuId();
        if (menus.containsKey(menuId)) {
            menus.put(menuId, updatedMenu);
            return true;
        }
        return false;
    }

    public void addDish(Dish dish) {
        dishes.put(dish.getDishId(), dish);
    }

    public Dish getDish(Integer id) {
        return dishes.get(id);
    }

    public Map<Integer, Dish> getAllDishes() {
        return dishes;
    }

    public Boolean deleteDish(Integer id) {
        return dishes.remove(id) != null;
    }

    public Boolean updateDish(Dish updatedDish) {
        Integer dishId = updatedDish.getDishId();
        if (dishes.containsKey(dishId)) {
            dishes.put(dishId, updatedDish);
            return true;
        }
        return false;
    }

    public void addDishReview(Integer dishId, Review review) {
        Dish dish = getDish(dishId);
        if (dish != null && review instanceof DishReview) {
            dish.addReview((DishReview) review);
        }
    }

    public List<Review> getDishReviews(Integer dishId) {
        Dish dish = getDish(dishId);
        return dish != null ? new ArrayList<>(dish.getReviews()) : new ArrayList<>();
    }

    private void initialize() {
        Dish parrillaDish1 = new Dish("Empanadas de carne", "Deliciosas empanadas rellenas de carne cortada a cuchillo.", 3.5f);
        Dish parrillaDish2 = new Dish("Asado de tira", "Corte clásico argentino cocido a la parrilla.", 15.0f);
        Dish parrillaDish3 = new Dish("Flan con dulce de leche", "Flan casero acompañado de dulce de leche artesanal.", 5.0f);
        addDish(parrillaDish1);
        addDish(parrillaDish2);
        addDish(parrillaDish3);

        Dish pastasDish1 = new Dish("Ravioles de ricota y espinaca", "Ravioles rellenos de ricota y espinaca con salsa de tomate.", 10.0f);
        Dish pastasDish2 = new Dish("Fideos a la bolognesa", "Fideos caseros con salsa bolognesa y queso rallado.", 9.0f);
        Dish pastasDish3 = new Dish("Tiramisú", "Tiramisú casero con café y licor.", 6.0f);
        addDish(pastasDish1);
        addDish(pastasDish2);
        addDish(pastasDish3);

        Dish urbanaDish1 = new Dish("Hamburguesa con cheddar", "Hamburguesa de carne con queso cheddar y panceta.", 12.0f);
        Dish urbanaDish2 = new Dish("Ensalada de pollo", "Ensalada de pollo con vegetales frescos y aderezo de mostaza.", 8.0f);
        Dish urbanaDish3 = new Dish("Brownie con helado", "Brownie de chocolate con helado de vainilla.", 7.0f);
        addDish(urbanaDish1);
        addDish(urbanaDish2);
        addDish(urbanaDish3);

        parrillaDish1.addReview(new DishReview("Sabor increíble, me encantaron.", 4.8f, 4.9f));
        parrillaDish2.addReview(new DishReview("Excelente corte de carne.", 5.0f, 4.8f));
        parrillaDish3.addReview(new DishReview("El flan estaba delicioso.", 4.5f, 4.7f));

        pastasDish1.addReview(new DishReview("La pasta estaba perfectamente cocida.", 4.7f, 4.8f));
        pastasDish2.addReview(new DishReview("La bolognesa estaba muy buena.", 4.6f, 4.5f));
        pastasDish3.addReview(new DishReview("El tiramisú es espectacular.", 4.9f, 5.0f));

        urbanaDish1.addReview(new DishReview("La hamburguesa estaba deliciosa.", 4.7f, 4.8f));
        urbanaDish2.addReview(new DishReview("Fresca y bien servida.", 4.5f, 4.4f));
        urbanaDish3.addReview(new DishReview("Perfecto balance de dulce.", 4.8f, 4.9f));

        Menu parrillaMenu = new Menu("Parrilla", new ArrayList<>(List.of(
                getDish(parrillaDish1.getDishId()),
                getDish(parrillaDish2.getDishId()),
                getDish(parrillaDish3.getDishId())
        )));
        Menu pastasMenu = new Menu("Pastas", new ArrayList<>(List.of(
                getDish(pastasDish1.getDishId()),
                getDish(pastasDish2.getDishId()),
                getDish(pastasDish3.getDishId())
        )));
        Menu urbanaMenu = new Menu("Comida Urbana", new ArrayList<>(List.of(
                getDish(urbanaDish1.getDishId()),
                getDish(urbanaDish2.getDishId()),
                getDish(urbanaDish3.getDishId())
        )));

        addMenu(parrillaMenu);
        addMenu(pastasMenu);
        addMenu(urbanaMenu);
    }
}
