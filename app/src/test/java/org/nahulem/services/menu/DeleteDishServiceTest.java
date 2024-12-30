package org.nahulem.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteDishServiceTest {
    private DeleteDishService mockDeleteDishService;
    private SelectDishService mockSelectDishService;
    private DataRepository mockDataRepository;

    @BeforeEach
    void setUp() {
        mockSelectDishService = mock(SelectDishService.class);
        mockDataRepository = mock(DataRepository.class);
        mockDeleteDishService = new DeleteDishService(mockDataRepository, mockSelectDishService);
    }

    @Test
    void testNullDish() {
        when(mockSelectDishService.execute()).thenReturn(null);

        Boolean result = mockDeleteDishService.execute();

        assertFalse(result);
    }

    @Test
    void testDish() {
        Dish dish = new Dish("Paella", "Paella de Mariscos", 10.0f);
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);

        Menu menu = new Menu("Comida española", dishes);
        Restaurant restaurant = new Restaurant("Restaurante Español", "Restaurante de comida española", "Calle España", menu);

        when(mockSelectDishService.execute()).thenReturn(dish);
        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of( 1, restaurant));

        Boolean result = mockDeleteDishService.execute();

        assertTrue(result);
        assertEquals(0, restaurant.getMenu().getDishes().size());

        verify(mockSelectDishService).execute();
        verify(mockDataRepository).getAllRestaurants();

    }
}