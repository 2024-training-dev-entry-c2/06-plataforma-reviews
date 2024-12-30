package restaurant.controller.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.dish.AddDish;

import static org.mockito.Mockito.*;

public class AddDishControllerTest {
    private AddDish addDish;
    private AddDishController addDishController;

    @BeforeEach
    public void setUp() {
        addDish = mock(AddDish.class);
        addDishController = new AddDishController(addDish);
    }

    @Test
    public void testExecute() {
        addDishController.execute();
        verify(addDish, times(1)).execute();
    }
}