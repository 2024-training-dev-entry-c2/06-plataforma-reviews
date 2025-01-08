package org.nahulem.controllers.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.services.restaurant.DeleteRestaurantService;
import org.nahulem.utils.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteRestaurantControllerTest {
    private DeleteRestaurantService mockDeleteRestaurantService;
    private DeleteRestaurantController deleteRestaurantController;
    private Validator mockValidator;

    @BeforeEach
    void setUp() {
        mockDeleteRestaurantService = mock(DeleteRestaurantService.class);
        deleteRestaurantController = new DeleteRestaurantController(mockDeleteRestaurantService);
    }

    @Test
    void execute() {
        when(mockDeleteRestaurantService.execute()).thenReturn(true);

        deleteRestaurantController.execute();
        verify(mockDeleteRestaurantService).execute();
    }

    @Test
    void testWithFalse() {
        when(mockDeleteRestaurantService.execute()).thenReturn(false);

        deleteRestaurantController.execute();
        verify(mockDeleteRestaurantService).execute();
    }
}