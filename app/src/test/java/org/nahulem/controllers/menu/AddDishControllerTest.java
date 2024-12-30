package org.nahulem.controllers.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishControllerTest {
    private Validator mockValidator;
    private AddDishService mockAddDishService;
    private AddDishController addDishController;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockAddDishService = mock(AddDishService.class);
        addDishController = new AddDishController(mockValidator, mockAddDishService);
    }

    @Test
    void execute() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Paella", "Paella de Mariscos", 10.0f));
        Menu menu = new Menu("Comida española", dishes);

        when(mockAddDishService.execute()).thenReturn(menu);

        addDishController.execute();

        verify(mockAddDishService).execute();
    }
}