package org.nahulem.services.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.utils.Validator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelectRestaurantTest {
    private DataRepository mockDataRepository;
    private SelectRestaurantService mockSelectRestaurantService;
    private Validator mockValidator;

    @BeforeEach
    void setUp() {
        mockDataRepository = mock(DataRepository.class);
        mockValidator = mock(Validator.class);
        mockSelectRestaurantService = new SelectRestaurantService(mockDataRepository, mockValidator);
    }

    @Test
    void testValidSelection() {
        Menu menu = mock(Menu.class);

        Restaurant restaurant1 = new Restaurant("Platos Rotos", "Gran Restaurante", "Rivera", null);
        Restaurant restaurant2 = new Restaurant("La Casona", "Restaurante de comida casera", "Rivera", null);
        Restaurant restaurant3 = new Restaurant("La Pasiva", "Restaurante de comida rápida", "Rivera", null);

        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2, 3, restaurant3));
        when(mockValidator.readInt(anyString())).thenReturn(1);

        Restaurant result = mockSelectRestaurantService.execute();

        assertNotNull(result);
        assertEquals(restaurant1.getName(), result.getName());
        assertEquals(restaurant1.getDescription(), result.getDescription());
        assertEquals(restaurant1.getLocation(), result.getLocation());

        when(mockValidator.readInt(anyString())).thenReturn(2);
        verify(mockDataRepository).getAllRestaurants();
    }

    @Test
    void testNoRestaurants() {
        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of());

        Restaurant result = mockSelectRestaurantService.execute();

        assertNull(result);

        verify(mockDataRepository).getAllRestaurants();
        verify(mockValidator, never()).readInt(anyString());
    }

    @Test
    void testInvalidSelectionBelow() {
        Menu menu = mock(Menu.class);

        Restaurant restaurant1 = new Restaurant("Platos Rotos", "Gran Restaurante", "Rivera", null);
        Restaurant restaurant2 = new Restaurant("La Casona", "Restaurante de comida casera", "Rivera", null);

        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2));
        when(mockValidator.readInt(anyString())).thenReturn(0, 2);

        Restaurant result = mockSelectRestaurantService.execute();

        assertNotNull(result);
        assertEquals(restaurant2.getName(), result.getName());

        verify(mockValidator, times(2)).readInt(anyString());
        verify(mockDataRepository).getAllRestaurants();
    }

    @Test
    void testInvalidSelectionAbove() {
        Menu menu = mock(Menu.class);

        Restaurant restaurant1 = new Restaurant("Platos Rotos", "Gran Restaurante", "Rivera", null);
        Restaurant restaurant2 = new Restaurant("La Casona", "Restaurante de comida casera", "Rivera", null);

        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2));
        when(mockValidator.readInt(anyString())).thenReturn(3, 2);

        Restaurant result = mockSelectRestaurantService.execute();

        assertNotNull(result);
        assertEquals(restaurant2.getName(), result.getName());

        verify(mockValidator, times(2)).readInt(anyString());
        verify(mockDataRepository).getAllRestaurants();
    }

    @Test
    void testShowRestaurants() {
        Restaurant restaurant1 = new Restaurant("Platos Rotos", "Gran Restaurante", "Rivera", null);
        Restaurant restaurant2 = new Restaurant("La Casona", "Restaurante de comida casera", "Rivera", null);

        when(mockDataRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2));
        when(mockValidator.readInt(anyString())).thenReturn(1);

        mockSelectRestaurantService.execute();

        verify(mockValidator).printMessage("Lista de Restaurantes: \n-------------------");
        verify(mockValidator).printMessage("1. Platos Rotos\nRivera\nGran Restaurante\n-------------------");
        verify(mockValidator).printMessage("2. La Casona\nRivera\nRestaurante de comida casera\n-------------------");
    }


}