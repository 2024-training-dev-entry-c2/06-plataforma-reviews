package restaurant.controller.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.restaurant.EditRestaurant;

import static org.mockito.Mockito.*;

public class EditRestaurantControllerTest {
    private EditRestaurant editRestaurant;
    private EditRestaurantController editRestaurantController;

    @BeforeEach
    public void setUp() {
        editRestaurant = mock(EditRestaurant.class);
        editRestaurantController = new EditRestaurantController(editRestaurant);
    }

    @Test
    public void testExecute() {
        editRestaurantController.execute();
        verify(editRestaurant, times(1)).execute();
    }
}