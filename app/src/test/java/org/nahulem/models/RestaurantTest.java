package org.nahulem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantTest {
    private Restaurant restaurant;
    private Menu mockMenu;

    @BeforeEach
    void setUp() {
        mockMenu = mock(Menu.class);
        when(mockMenu.getName()).thenReturn("Menú de Prueba");
        when(mockMenu.getDishes()).thenReturn(List.of());

        restaurant = new Restaurant("Restaurante Test", "Descripción Test", "Ubicación Test", mockMenu);
    }

    @Test
    void toStringWithoutReviewsOrDishes() {
        String expected = """
                ===============================================
                Nombre: Restaurante Test
                Descripción: Descripción Test
                Locación: Ubicación Test
                Calificacion: 0.0
                Menú: Menú de Prueba
                
                Platos:
                
                Reseñas del restaurante:
                  No hay reseñas.
                """;

        assertEquals(expected, restaurant.toString());
    }

    @Test
    void toStringWithReviewsAndDishes() {
        Dish mockDish = mock(Dish.class);
        when(mockDish.toString()).thenReturn("Plato de prueba");
        when(mockMenu.getDishes()).thenReturn(List.of(mockDish));

        Review mockReview = mock(Review.class);
        when(mockReview.toString()).thenReturn("Reseña de prueba");
        restaurant.addReview(mockReview);

        String result = restaurant.toString();

        String expectedDish = """
                ===============================================
                Nombre: Restaurante Test
                Descripción: Descripción Test
                Locación: Ubicación Test
                Calificacion: 0.0
                Menú: Menú de Prueba
                
                Platos:
                Plato de prueba
                
                Reseñas del restaurante:
                  - Reseña de prueba
                """;

        assertEquals(expectedDish, result);
    }
}
