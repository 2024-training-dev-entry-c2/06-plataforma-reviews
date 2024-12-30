package restaurant.controller.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.restaurant.ListAllRestaurants;

import static org.mockito.Mockito.*;

public class ListAllRestaurantsControllerTest {
    private ListAllRestaurants listAllRestaurants;
    private ListAllRestaurantsController listAllRestaurantsController;

    @BeforeEach
    public void setUp() {
        listAllRestaurants = mock(ListAllRestaurants.class);
        listAllRestaurantsController = new ListAllRestaurantsController(listAllRestaurants);
    }

    @Test
    public void testExecute() {
        listAllRestaurantsController.execute();
        verify(listAllRestaurants, times(1)).execute();
    }
}