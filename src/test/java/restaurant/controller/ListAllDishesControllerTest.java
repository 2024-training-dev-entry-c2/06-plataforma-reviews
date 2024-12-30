package restaurant.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.dish.ListAllDishes;

import static org.mockito.Mockito.*;

public class ListAllDishesControllerTest {
    private ListAllDishes listAllDishes;
    private ListAllDishesController listAllDishesController;

    @BeforeEach
    public void setUp() {
        listAllDishes = mock(ListAllDishes.class);
        listAllDishesController = new ListAllDishesController(listAllDishes);
    }

    @Test
    public void testExecute() {
        listAllDishesController.execute();
        verify(listAllDishes, times(1)).execute();
    }
}