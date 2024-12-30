package org.nahulem.controllers.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Restaurant;
import org.nahulem.services.menu.UpdateDishService;

import static org.mockito.Mockito.*;

class UpdateDishControllerTest {
    private UpdateDishService mockUpdateDish;
    private UpdateDishController updateDishController;

    @BeforeEach
    void setUp() {
        mockUpdateDish = mock(UpdateDishService.class);
        updateDishController = new UpdateDishController(mockUpdateDish);
    }

    @Test
    void executeWithRestaurant() {
        Restaurant mockRestaurant = mock(Restaurant.class);
        when(mockUpdateDish.execute()).thenReturn(mockRestaurant);

        updateDishController.execute();

        verify(mockUpdateDish).execute();
    }

    @Test
    void executeWithNull() {
        when(mockUpdateDish.execute()).thenReturn(null);

        updateDishController.execute();

        verify(mockUpdateDish).execute();
    }
}
