package org.nahulem.services.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteRestaurantServiceTest {
    private DataRepository mockDataRepository;
    private SelectRestaurantService mockSelectRestaurantService;
    private DeleteRestaurantService deleteRestaurantService;

    @BeforeEach
    void setUp() {
        mockDataRepository = mock(DataRepository.class);
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        deleteRestaurantService = new DeleteRestaurantService(mockDataRepository, mockSelectRestaurantService);
    }

    @Test
    void testRestaurantIsNull() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);
        boolean result = deleteRestaurantService.execute();
        assertFalse(result);
    }

    @Test
    void testRestaurantDeleted() {
        Restaurant mockRestaurant = mock(Restaurant.class);
        when(mockRestaurant.getRestaurantId()).thenReturn(1);
        when(mockSelectRestaurantService.execute()).thenReturn(mockRestaurant);
        when(mockDataRepository.deleteRestaurant(1)).thenReturn(true);
        boolean result = deleteRestaurantService.execute();
        assertTrue(result);
        verify(mockDataRepository, times(1)).deleteRestaurant(1);
    }

    @Test
    void testRestaurantDeletedFailed() {
        Restaurant mockRestaurant = mock(Restaurant.class);
        when(mockRestaurant.getRestaurantId()).thenReturn(1);
        when(mockSelectRestaurantService.execute()).thenReturn(mockRestaurant);
        when(mockDataRepository.deleteRestaurant(1)).thenReturn(false);
        boolean result = deleteRestaurantService.execute();
        assertFalse(result);
        verify(mockDataRepository, times(1)).deleteRestaurant(1);
    }
}
