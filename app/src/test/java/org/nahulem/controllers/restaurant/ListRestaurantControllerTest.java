package org.nahulem.controllers.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.services.restaurant.ListRestaurantService;
import org.nahulem.utils.Validator;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantControllerTest {
    private ListRestaurantService mockListRestaurantService;
    private ListRestaurantController listRestaurantController;
    private Validator mockValidator;

    @BeforeEach
    void setUp() {
        mockListRestaurantService = mock(ListRestaurantService.class);
        mockValidator = mock(Validator.class);
        listRestaurantController = new ListRestaurantController(mockListRestaurantService);
    }

    @Test
    void execute() {
        Menu mockMenu = mock(Menu.class);
        List<Restaurant> restaurants = List.of(new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu));

        when(mockListRestaurantService.execute()).thenReturn(restaurants);

        listRestaurantController.execute();

        verify(mockListRestaurantService).execute();
    }

    @Test
    void testWithEmptyList() {
        when(mockListRestaurantService.execute()).thenReturn(List.of());

        listRestaurantController.execute();

        verify(mockListRestaurantService).execute();
    }
}