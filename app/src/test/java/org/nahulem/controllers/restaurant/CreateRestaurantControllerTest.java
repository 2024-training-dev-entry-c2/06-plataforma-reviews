package org.nahulem.controllers.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.services.restaurant.CreateRestaurantService;
import org.nahulem.utils.Validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantControllerTest {
    private CreateRestaurantService mockCreateRestaurantService;
    private Validator mockValidator;
    private CreateRestaurantController createRestaurantController;

    @BeforeEach
    void setUp() {
        mockCreateRestaurantService = mock(CreateRestaurantService.class);
        mockValidator = mock(Validator.class);
        createRestaurantController = new CreateRestaurantController(mockCreateRestaurantService);
    }

    @Test
    void execute() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);
        when(mockCreateRestaurantService.execute()).thenReturn(restaurant);

        createRestaurantController.execute();

        verify(mockCreateRestaurantService).execute();
    }
}