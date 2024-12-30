package restaurant.controller.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.dish.EditDish;

import static org.mockito.Mockito.*;

public class EditDishControllerTest {
    private EditDish editDish;
    private EditDishController editDishController;

    @BeforeEach
    public void setUp() {
        editDish = mock(EditDish.class);
        editDishController = new EditDishController(editDish);
    }

    @Test
    public void testExecute() {
        editDishController.execute();
        verify(editDish, times(1)).execute();
    }
}