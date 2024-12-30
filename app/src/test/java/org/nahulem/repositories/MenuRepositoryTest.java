package org.nahulem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Review;
import org.nahulem.models.DishReview;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MenuRepositoryTest {
    private MenuRepository menuRepo;

    @BeforeEach
    void setUp() {
        menuRepo = MenuRepository.getInstance();
        menuRepo.clear();
    }

    @Test
    void addMenu() {
        Menu menu = new Menu("Menú de prueba", null);
        menuRepo.addMenu(menu);
        assertNotNull(menuRepo.getAllMenus());
    }

    @Test
    void deleteMenuExists() {
        Menu menu = new Menu("Menú de prueba", null);
        menuRepo.addMenu(menu);
        assertTrue(menuRepo.deleteMenu(menu.getMenuId()));
    }

    @Test
    void deleteMenuNotExists() {
        assertFalse(menuRepo.deleteMenu(999));
    }

    @Test
    void updateMenuExists() {
        Menu menu = new Menu("Menú de prueba", null);
        menuRepo.addMenu(menu);
        Menu updatedMenu = new Menu("Menú actualizado", null);
        menuRepo.addMenu(updatedMenu);
        assertTrue(menuRepo.updateMenu(updatedMenu));
        assertEquals("Menú actualizado", updatedMenu.getName());
    }

    @Test
    void updateMenuNotExists() {
        Menu updatedMenu = new Menu("Menú inexistente", null);
        assertFalse(menuRepo.updateMenu(updatedMenu));
    }

    @Test
    void getAllDishes() {
        Dish dish1 = new Dish("Plato 1", "Descripción 1", 10.0f);
        Dish dish2 = new Dish("Plato 2", "Descripción 2", 20.0f);
        menuRepo.addDish(dish1);
        menuRepo.addDish(dish2);
        assertEquals(2, menuRepo.getAllDishes().size());
    }

    @Test
    void deleteDishExists() {
        Dish dish = new Dish("Plato de prueba", "Descripción de prueba", 10.0f);
        menuRepo.addDish(dish);
        assertTrue(menuRepo.deleteDish(dish.getDishId()));
    }

    @Test
    void deleteDishNotExists() {
        assertFalse(menuRepo.deleteDish(999));
    }

    @Test
    void updateDishExists() {
        Dish dish = new Dish("Plato de prueba", "Descripción de prueba", 10.0f);
        menuRepo.addDish(dish);
        Dish updatedDish = new Dish("Plato actualizado", "Descripción actualizada", 15.0f);
        menuRepo.addDish(updatedDish);
        assertTrue(menuRepo.updateDish(updatedDish));
        assertEquals("Plato actualizado", updatedDish.getName());
    }

    @Test
    void updateDishNotExists() {
        Dish updatedDish = new Dish("Plato inexistente", "Descripción de prueba", 10.0f);
        assertFalse(menuRepo.updateDish(updatedDish));
    }

    @Test
    void addDishReviewValid() {
        Dish dish = new Dish("Plato de prueba", "Descripción de prueba", 10.0f);
        menuRepo.addDish(dish);
        DishReview review = new DishReview("Muy bueno", 4.5f, 4.5f);
        menuRepo.addDishReview(dish.getDishId(), review);
        assertEquals(1, menuRepo.getDishReviews(dish.getDishId()).size());
        assertEquals(review, menuRepo.getDishReviews(dish.getDishId()).get(0));
    }

    @Test
    void addDishReviewDishNotExists() {
        DishReview review = new DishReview("Muy bueno", 4.5f, 3.4f);
        menuRepo.addDishReview(999, review);
        assertTrue(menuRepo.getDishReviews(999).isEmpty());
    }

    @Test
    void addDishReviewInvalid() {
        Dish dish = new Dish("Plato de prueba", "Descripción de prueba", 10.0f);
        menuRepo.addDish(dish);
        Review genericReview = mock(Review.class);
        menuRepo.addDishReview(dish.getDishId(), genericReview);
        assertTrue(menuRepo.getDishReviews(dish.getDishId()).isEmpty());
    }

    @Test
    void getDishReviewsDishNotExists() {
        assertTrue(menuRepo.getDishReviews(999).isEmpty());
    }

    @Test
    void getDishReviewsNoReviews() {
        Dish dish = new Dish("Plato de prueba", "Descripción de prueba", 10.0f);
        menuRepo.addDish(dish);
        assertTrue(menuRepo.getDishReviews(dish.getDishId()).isEmpty());
    }
}
