package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.utils.ConsoleUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    private ConsoleUtil mockConsole;
    private CreateRestaurantImpl mockCreateRestaurantImpl;
    private FindRestaurantsImpl mockFindRestaurantsImpl;
    private UpdateRestaurantImpl mockUpdateRestaurantImpl;
    private RemoveRestaurantImpl mockRemoveRestaurantImpl;
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        mockConsole = mock(ConsoleUtil.class);
        mockCreateRestaurantImpl = mock(CreateRestaurantImpl.class);
        mockFindRestaurantsImpl = mock(FindRestaurantsImpl.class);
        mockUpdateRestaurantImpl = mock(UpdateRestaurantImpl.class);
        mockRemoveRestaurantImpl = mock(RemoveRestaurantImpl.class);

        restaurantService = new RestaurantService(mockConsole);
        restaurantService.createRestaurantImpl = mockCreateRestaurantImpl;
        restaurantService.findRestaurantsImpl = mockFindRestaurantsImpl;
        restaurantService.updateRestaurantImpl = mockUpdateRestaurantImpl;
        restaurantService.removeRestaurantImpl = mockRemoveRestaurantImpl;
    }

    @Test
    @DisplayName("Should create a restaurant successfully")
    void testCreateRestaurant() {
        Restaurant mockRestaurant = mock(Restaurant.class);
        when(mockCreateRestaurantImpl.execute()).thenReturn(mockRestaurant);

        Restaurant result = restaurantService.createRestaurant();

        assertNotNull(result);
        assertEquals(mockRestaurant, result);
        verify(mockCreateRestaurantImpl).execute();
    }

    @Test
    @DisplayName("Should find all restaurants successfully")
    void testFindAllRestaurants() {
        Map<Integer, Restaurant> mockRestaurants = Map.of(1, mock(Restaurant.class));
        when(mockFindRestaurantsImpl.execute()).thenReturn(mockRestaurants);

        Map<Integer, Restaurant> result = restaurantService.findAllRestaurants();

        assertNotNull(result);
        assertEquals(mockRestaurants, result);
        verify(mockFindRestaurantsImpl).execute();
    }

    @Test
    @DisplayName("Should update a restaurant successfully")
    void testUpdateRestaurant() {
        Restaurant mockRestaurant = mock(Restaurant.class);
        when(mockUpdateRestaurantImpl.execute()).thenReturn(mockRestaurant);

        Restaurant result = restaurantService.updateRestaurant();

        assertNotNull(result);
        assertEquals(mockRestaurant, result);
        verify(mockUpdateRestaurantImpl).execute();
    }

    @Test
    @DisplayName("Should remove a restaurant successfully")
    void testRemoveRestaurant() {
        when(mockRemoveRestaurantImpl.execute()).thenReturn(true);

        Boolean result = restaurantService.removeRestaurant();

        assertTrue(result);
        verify(mockRemoveRestaurantImpl).execute();
    }
}
