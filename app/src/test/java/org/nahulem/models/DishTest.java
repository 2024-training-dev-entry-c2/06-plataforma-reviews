package org.nahulem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    private Dish dish;

    @BeforeEach
    void setUp() {
        dish = new Dish("Pizza", "Deliciosa pizza con queso", 10.0f);
    }

    @Test
    void addObserver() {
        UserObserver userObserver = new UserObserver("User1");
        dish.addObserver(userObserver);

        assertDoesNotThrow(() -> dish.addObserver(userObserver));
    }

    @Test
    void notifyObservers() {
        UserObserver observer1 = new UserObserver("User1");
        UserObserver observer2 = new UserObserver("User2");

        dish.addObserver(observer1);
        dish.addObserver(observer2);

        String message = "Nuevo plato disponible";
        dish.notifyObservers(message);

        assertEquals(message, observer1.getLastMessage());
        assertEquals(message, observer2.getLastMessage());
    }

    @Test
    void toStringWithReviews() {
        Review review1 = new DishReview("Excelente sabor", 5.0f, 4.5f);
        Review review2 = new DishReview("Buena textura", 4.0f, 4.2f);

        dish.addReview(review1);
        dish.addReview(review2);

        String expectedOutput = """
                Nombre: Pizza
                Descripción: Deliciosa pizza con queso
                Precio: $10.0
                Reseñas del plato Pizza :
                    - Excelente sabor
                    - Buena textura
                ____________________________________________________
                """;
        assertTrue(dish.toString().contains("Excelente sabor"));
        assertTrue(dish.toString().contains("Buena textura"));
    }

    @Test
    void toStringWithoutReviews() {
        String expectedOutput = """
                Nombre: Pizza
                Descripción: Deliciosa pizza con queso
                Precio: $10.0
                Reseñas del plato Pizza : No hay reseñas.
                """;
        assertTrue(dish.toString().contains("No hay reseñas."));
    }
}
