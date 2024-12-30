package org.nahulem.services.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantTest {
    private RestaurantRepository mockRepository;
    private Validator mockValidator;
    private AddDishService mockAddDishService;
    private CreateRestaurantService mockCreateRestaurantService;

    @BeforeEach
    void setUp() {
        mockRepository = mock(RestaurantRepository.class);
        mockValidator = mock(Validator.class);
        mockAddDishService = mock(AddDishService.class);
        mockCreateRestaurantService = new CreateRestaurantService(mockAddDishService, mockRepository, mockValidator);
    }

    @Test
    void testExecute() {
        String name = "Resto Test";
        String description = "Test";
        String location = "Location Test";

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Nuevo Plato", "Descripción Test", 5.0f));
        Menu menu = new Menu("Menu Test", dishes);

        when(mockValidator.readString(anyString())).thenReturn(name, description, location);
        when(mockAddDishService.execute()).thenReturn(menu);

        Restaurant result = mockCreateRestaurantService.execute();

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
        assertEquals(location, result.getLocation());
        assertEquals(menu.getName(), result.getMenu().getName());
        assertEquals(menu.getDishes().size(), result.getMenu().getDishes().size());

        verify(mockValidator, times(3)).readString(anyString());
        verify(mockRepository).addRestaurant(result);
        verify(mockAddDishService).execute();
    }
}
