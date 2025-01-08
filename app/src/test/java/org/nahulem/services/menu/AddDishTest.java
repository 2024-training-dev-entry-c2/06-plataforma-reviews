package org.nahulem.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.MenuRepository;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishTest {
    private Validator validator;
    private MenuRepository menuRepository;
    private SelectRestaurantService selectRestaurantService;
    private AddDishService addDishService;

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
        menuRepository = mock(MenuRepository.class);
        selectRestaurantService = mock(SelectRestaurantService.class);
        addDishService = new AddDishService(validator, menuRepository, selectRestaurantService);
    }

    @Test
    void testNullRestaurant() {
        when(selectRestaurantService.execute()).thenReturn(null);

        Menu result = addDishService.execute();

        assertNull(result);
        verify(selectRestaurantService).execute();
    }

    @Test
    void testNullMenu() {
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", null);

        when(selectRestaurantService.execute()).thenReturn(restaurant);

        when(validator.readString("Ingresa el nombre del menú: \n")).thenReturn("Menú del día");

        when(validator.readString("Ingrese el nombre del plato: \n")).thenReturn("Paella");
        when(validator.readString("Ingrese la descripción del plato: \n")).thenReturn("Paella de mariscos");
        when(validator.readFloat("Ingrese el precio del plato: \n")).thenReturn(12.5f);
        when(validator.readBoolean("¿Desea agregar otro plato al menú? (S/N): \n")).thenReturn(false);

        Menu result = addDishService.execute();

        assertNotNull(result);
        assertEquals("Menú del día", result.getName());
        assertEquals(1, result.getDishes().size());
        assertEquals("Paella", result.getDishes().get(0).getName());
        assertEquals("Paella de mariscos", result.getDishes().get(0).getDescription());
        assertEquals(12.5f, result.getDishes().get(0).getPrice());

        verify(selectRestaurantService).execute();
        verify(validator).readString("Ingresa el nombre del menú: \n");
        verify(validator).readString("Ingrese el nombre del plato: \n");
        verify(validator).readString("Ingrese la descripción del plato: \n");
        verify(validator).readFloat("Ingrese el precio del plato: \n");
        verify(validator).readBoolean("¿Desea agregar otro plato al menú? (S/N): \n");

        assertEquals(result, restaurant.getMenu());
        verify(menuRepository).addMenu(result);
    }

    @Test
    void testExistingMenu() {
        Menu existingMenu = new Menu("Menú Existente", new ArrayList<>());
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", existingMenu);

        when(selectRestaurantService.execute()).thenReturn(restaurant);

        when(validator.readString("Ingrese el nombre del plato: \n")).thenReturn("Paella");
        when(validator.readString("Ingrese la descripción del plato: \n")).thenReturn("Paella de mariscos");
        when(validator.readFloat("Ingrese el precio del plato: \n")).thenReturn(12.5f);
        when(validator.readBoolean("¿Desea agregar otro plato al menú? (S/N): \n")).thenReturn(false);

        Menu result = addDishService.execute();

        assertNotNull(result);
        assertEquals(existingMenu, result);

        assertEquals(1, existingMenu.getDishes().size());
        assertEquals("Paella", existingMenu.getDishes().get(0).getName());
        assertEquals("Paella de mariscos", existingMenu.getDishes().get(0).getDescription());
        assertEquals(12.5f, existingMenu.getDishes().get(0).getPrice());

        verify(selectRestaurantService).execute();
        verify(validator).readString("Ingrese el nombre del plato: \n");
        verify(validator).readString("Ingrese la descripción del plato: \n");
        verify(validator).readFloat("Ingrese el precio del plato: \n");
        verify(validator).readBoolean("¿Desea agregar otro plato al menú? (S/N): \n");
        verify(menuRepository).addDish(any(Dish.class));
    }
}
