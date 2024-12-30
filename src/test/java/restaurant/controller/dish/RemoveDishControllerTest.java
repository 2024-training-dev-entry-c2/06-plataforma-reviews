package restaurant.controller.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.dish.RemoveDish;

import static org.mockito.Mockito.*;

public class RemoveDishControllerTest {
    private RemoveDish removeDish;
    private RemoveDishController removeDishController;

    @BeforeEach
    public void setUp() {
        removeDish = mock(RemoveDish.class);
        removeDishController = new RemoveDishController(removeDish);
    }

    @Test
    public void testExecute() {
        removeDishController.execute();
        verify(removeDish, times(1)).execute();
    }
}