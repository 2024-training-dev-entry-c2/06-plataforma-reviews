package org.nahulem.controllers.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.services.menu.DeleteDishService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteDishControllerTest {
    private DeleteDishService mockDeleteDishService;
    private DeleteDishController deleteDishController;

    @BeforeEach
    void setUp() {
        mockDeleteDishService = mock(DeleteDishService.class);
        deleteDishController = new DeleteDishController(mockDeleteDishService);
    }

    @Test
    void execute() {
        when(mockDeleteDishService.execute()).thenReturn(true);

        deleteDishController.execute();
        verify(mockDeleteDishService).execute();
    }

    @Test
    void testWithFalse() {
        when(mockDeleteDishService.execute()).thenReturn(false);

        deleteDishController.execute();
        verify(mockDeleteDishService).execute();
    }
}