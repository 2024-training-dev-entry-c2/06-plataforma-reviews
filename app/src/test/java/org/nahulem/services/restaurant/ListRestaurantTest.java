package org.nahulem.services.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantTest {
    private DataRepository mockDataRepository;
    private ListRestaurantService mockListRestaurantService;

    @BeforeEach
    void setUp() {
        mockDataRepository = mock(DataRepository.class);
        mockListRestaurantService = new ListRestaurantService(mockDataRepository);
    }

    @Test
    void testExecute() {
        Map<Integer, Restaurant> restaurants = new HashMap<>();
        restaurants.put(1, new Restaurant("Platos Rotos", "Gran Restaurante", "Rivera", null));
        restaurants.put(2, new Restaurant("La Casona", "Restaurante de comida casera", "Rivera", null));
        restaurants.put(3, new Restaurant("La Pasiva", "Restaurante de comida rápida", "Rivera", null));

        when(mockDataRepository.getAllRestaurants()).thenReturn(restaurants);

        List<Restaurant> result = mockListRestaurantService.execute();

        assertNotNull(result);
        assertEquals(restaurants.size(), result.size());
        assertEquals(restaurants.get(1).getName(), result.get(0).getName());
        assertEquals(restaurants.get(2).getName(), result.get(1).getName());
        assertEquals(restaurants.get(3).getName(), result.get(2).getName());

        verify(mockDataRepository).getAllRestaurants();

    }
}