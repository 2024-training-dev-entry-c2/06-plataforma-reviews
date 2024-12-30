package org.nahulem.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.utils.Validator;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateDishTest {
    private Validator mockValidator;
    private DataRepository mockRepository;
    private SelectDishService mockSelectDishService;
    private UpdateDishService mockUpdateDishService;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockRepository = mock(DataRepository.class);
        mockSelectDishService = mock(SelectDishService.class);
        mockUpdateDishService = new UpdateDishService(mockRepository, mockSelectDishService, mockValidator);
    }

    @Test
    void testNullDish() {
        when(mockSelectDishService.execute()).thenReturn(null);

        Restaurant result = mockUpdateDishService.execute();

        assertNull(result);
    }

    @Test
    void testDish() {
        Dish dish = new Dish("Paella", "Paella de Mariscos", 10.0f);
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", new Menu("Menú del día", List.of(dish)));

        when(mockSelectDishService.execute()).thenReturn(dish);
        when(mockValidator.readString("Ingresa el nuevo nombre del plato: ")).thenReturn("Paella 2");
        when(mockValidator.readString("Ingresa la nueva descripción del plato: ")).thenReturn("Paella de Mariscos 2");
        when(mockValidator.readFloat("Ingresa el nuevo precio del plato: ")).thenReturn(15.0f);
        when(mockRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant));

        Restaurant result = mockUpdateDishService.execute();

        assertNotNull(result);
        assertTrue(result.getMenu().getDishes().contains(dish));

        assertEquals("Paella 2", dish.getName());
        assertEquals("Paella de Mariscos 2", dish.getDescription());
        assertEquals(15.0f, dish.getPrice());

        assertEquals(result, mockRepository.getAllRestaurants().values().stream()
                .filter(rest -> rest.getMenu().getDishes().contains(dish))
                .findFirst().orElse(null));

        verify(mockSelectDishService).execute();
        verify(mockValidator, times(2)).readString(anyString());
        verify(mockValidator).readFloat(anyString());
    }

    @Test
    void testDishDetailsWithEmptyFields() {
        Dish dish = new Dish("Paella", "Paella de Mariscos", 10.0f);
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", new Menu("Menú del día", List.of(dish)));

        when(mockSelectDishService.execute()).thenReturn(dish);
        when(mockValidator.readString("Ingresa el nuevo nombre del plato: ")).thenReturn("", "");
        when(mockValidator.readString("Ingresa la nueva descripción del plato: ")).thenReturn("", "");
        when(mockValidator.readFloat("Ingresa el nuevo precio del plato: ")).thenReturn(0.0f);
        when(mockRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant));

        Restaurant result = mockUpdateDishService.execute();

        assertNotNull(result);
        assertTrue(result.getMenu().getDishes().contains(dish));

        assertEquals("Paella", dish.getName());
        assertEquals("Paella de Mariscos", dish.getDescription());
        assertEquals(10.0f, dish.getPrice());

        assertEquals(result, mockRepository.getAllRestaurants().values().stream()
                .filter(rest -> rest.getMenu().getDishes().contains(dish))
                .findFirst().orElse(null));

        verify(mockSelectDishService).execute();
        verify(mockValidator, times(2)).readString(anyString());
        verify(mockValidator).readFloat(anyString());
    }
}