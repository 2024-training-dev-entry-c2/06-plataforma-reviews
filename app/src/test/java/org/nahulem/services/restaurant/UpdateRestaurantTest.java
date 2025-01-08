package org.nahulem.services.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.utils.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateRestaurantTest {
    private SelectRestaurantService mockSelectRestaurantService;
    private RestaurantRepository mockRepository;
    private Validator mockValidator;
    private UpdateRestaurantService updateRestaurantService;


    @BeforeEach
    void setUp() {
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        mockRepository = mock(RestaurantRepository.class);
        mockValidator = mock(Validator.class);
        updateRestaurantService = new UpdateRestaurantService(mockRepository, mockSelectRestaurantService, mockValidator);
    }

    @Test
    void execute() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("La Pasiva", "Restaurante de comida rápida", "Rivera", mockMenu);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readString(anyString())).thenReturn("Nuevo Nombre Test", "Nueva Descripcion Test", "Nueva Ubicacion Test");
        when(mockRepository.updateRestaurant(restaurant)).thenReturn(true);

        Boolean result = updateRestaurantService.execute();

        assertNotNull(result);
        assertTrue(result);

        assertEquals("Nuevo Nombre Test", restaurant.getName());
        assertEquals("Nueva Descripcion Test", restaurant.getDescription());
        assertEquals("Nueva Ubicacion Test", restaurant.getLocation());

        verify(mockSelectRestaurantService).execute();
        verify(mockRepository).updateRestaurant(restaurant);
        verify(mockValidator, times(3)).readString(anyString());
    }

    @Test
    void testRestaurantIsNull() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);

        Boolean result = updateRestaurantService.execute();

        assertNotNull(result);
        assertEquals(false, result);

        verify(mockSelectRestaurantService).execute();
        verify(mockRepository, times(0)).updateRestaurant(null);
        verify(mockValidator, times(0)).readString(anyString());
    }


    @Test
    void testUpdateRestaurantEmptyFields() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("La Pasiva", "Restaurante de comida rápida", "Rivera", mockMenu);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readString(anyString())).thenReturn("", "", "");
        when(mockRepository.updateRestaurant(restaurant)).thenReturn(true);

        Boolean result = updateRestaurantService.execute();

        assertNotNull(result);
        assertEquals(true, result);

        assertEquals("La Pasiva", restaurant.getName());
        assertEquals("Restaurante de comida rápida", restaurant.getDescription());
        assertEquals("Rivera", restaurant.getLocation());

        verify(mockSelectRestaurantService).execute();
        verify(mockRepository).updateRestaurant(restaurant);
        verify(mockValidator, times(3)).readString(anyString());
    }

}