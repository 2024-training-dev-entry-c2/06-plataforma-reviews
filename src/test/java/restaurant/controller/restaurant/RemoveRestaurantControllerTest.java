package restaurant.controller.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.restaurant.RemoveRestaurant;

import static org.mockito.Mockito.*;

public class RemoveRestaurantControllerTest {
    private RemoveRestaurant removeRestaurant;
    private RemoveRestaurantController removeRestaurantController;

    @BeforeEach
    public void setUp() {
        removeRestaurant = mock(RemoveRestaurant.class);
        removeRestaurantController = new RemoveRestaurantController(removeRestaurant);
    }

    @Test
    public void testExecute() {
        removeRestaurantController.execute();
        verify(removeRestaurant, times(1)).execute();
    }
}