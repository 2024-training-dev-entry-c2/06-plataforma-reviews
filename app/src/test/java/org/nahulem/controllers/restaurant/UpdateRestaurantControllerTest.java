package org.nahulem.controllers.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.services.restaurant.UpdateRestaurantService;
import org.nahulem.utils.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateRestaurantControllerTest {
    private UpdateRestaurantService mockUpdateRestaurantService;
    private Validator mockValidator;
    private UpdateRestaurantController updateRestaurantController;

    @BeforeEach
    void setUp() {
        mockUpdateRestaurantService = mock(UpdateRestaurantService.class);
        mockValidator = mock(Validator.class);
        updateRestaurantController = new UpdateRestaurantController(mockUpdateRestaurantService);
    }

    @Test
    void execute() {
        when(mockUpdateRestaurantService.execute()).thenReturn(true);

        updateRestaurantController.execute();
        verify(mockUpdateRestaurantService).execute();
    }

    @Test
    void testWithFalse() {
        when(mockUpdateRestaurantService.execute()).thenReturn(false);

        updateRestaurantController.execute();
        verify(mockUpdateRestaurantService).execute();
    }
}