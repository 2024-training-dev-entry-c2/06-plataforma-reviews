package restaurant.controller.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.restaurant.AddRestaurant;
import restaurant.models.Restaurant;

import static org.mockito.Mockito.*;

public class AddRestaurantControllerTest {
    private AddRestaurant addRestaurant;
    private AddRestaurantController addRestaurantController;

    @BeforeEach
    public void setUp() {
        addRestaurant = mock(AddRestaurant.class);
        addRestaurantController = new AddRestaurantController(addRestaurant);
    }

    @Test
    public void testExecute() {
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        when(addRestaurant.execute()).thenReturn(restaurant);

        addRestaurantController.execute();

        verify(addRestaurant, times(1)).execute();
    }
}