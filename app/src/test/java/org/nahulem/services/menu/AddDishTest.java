package org.nahulem.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishTest {
    private Validator mockValidator;
    private DataRepository mockRepository;
    private SelectRestaurantService mockSelectRestaurantService;
    private AddDishService mockAddDishService;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockRepository = mock(DataRepository.class);
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        mockAddDishService = new AddDishService(mockValidator, mockRepository, mockSelectRestaurantService);
    }

    @Test
    void testNullRestaurant() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);

        Menu result = mockAddDishService.execute();

        assertNull(result);
        verify(mockSelectRestaurantService).execute();
    }

    @Test
    void testNullMenu() {
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", null);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);

        when(mockValidator.readString("Ingresa el nombre del menú: \n")).thenReturn("Menú del día");

        when(mockValidator.readString("Ingrese el nombre del plato: \n")).thenReturn("Paella");
        when(mockValidator.readString("Ingrese la descripción del plato: \n")).thenReturn("Paella de mariscos");
        when(mockValidator.readFloat("Ingrese el precio del plato: \n")).thenReturn(12.5f);
        when(mockValidator.readBoolean("¿Desea agregar otro plato al menú? (S/N): \n")).thenReturn(false);

        Menu result = mockAddDishService.execute();

        assertNotNull(result);
        assertEquals("Menú del día", result.getName());
        assertEquals(1, result.getDishes().size());
        assertEquals("Paella", result.getDishes().get(0).getName());
        assertEquals("Paella de mariscos", result.getDishes().get(0).getDescription());
        assertEquals(12.5f, result.getDishes().get(0).getPrice());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator).readString("Ingresa el nombre del menú: \n");
        verify(mockValidator).readString("Ingrese el nombre del plato: \n");
        verify(mockValidator).readString("Ingrese la descripción del plato: \n");
        verify(mockValidator).readFloat("Ingrese el precio del plato: \n");
        verify(mockValidator).readBoolean("¿Desea agregar otro plato al menú? (S/N): \n");
    }

    @Test
    void testExecuteWithMenu() {
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", null);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);

        when(mockValidator.readString("Ingresa el nombre del menú: \n")).thenReturn("Menú del día");

        when(mockValidator.readString("Ingrese el nombre del plato: \n"))
                .thenReturn("Paella", "Tortilla de patatas");
        when(mockValidator.readString("Ingrese la descripción del plato: \n"))
                .thenReturn("Paella de mariscos", "Tortilla de patatas con cebolla");
        when(mockValidator.readFloat("Ingrese el precio del plato: \n"))
                .thenReturn(12.5f, 10.0f);
        when(mockValidator.readBoolean("¿Desea agregar otro plato al menú? (S/N): \n"))
                .thenReturn(true, false);

        Menu result = mockAddDishService.execute();

        assertNotNull(result);
        assertEquals("Menú del día", result.getName());
        assertEquals(2, result.getDishes().size());

        assertEquals("Paella", result.getDishes().get(0).getName());
        assertEquals("Paella de mariscos", result.getDishes().get(0).getDescription());
        assertEquals(12.5f, result.getDishes().get(0).getPrice());

        assertEquals("Tortilla de patatas", result.getDishes().get(1).getName());
        assertEquals("Tortilla de patatas con cebolla", result.getDishes().get(1).getDescription());
        assertEquals(10.0f, result.getDishes().get(1).getPrice());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator).readString("Ingresa el nombre del menú: \n");
        verify(mockValidator, times(2)).readString("Ingrese el nombre del plato: \n");
        verify(mockValidator, times(2)).readString("Ingrese la descripción del plato: \n");
        verify(mockValidator, times(2)).readFloat("Ingrese el precio del plato: \n");
        verify(mockValidator, times(2)).readBoolean("¿Desea agregar otro plato al menú? (S/N): \n");
    }

}