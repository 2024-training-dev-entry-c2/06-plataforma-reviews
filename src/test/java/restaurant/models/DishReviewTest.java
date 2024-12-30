package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishReviewTest {
    private DishReview dishReview;
    private Dish dish;

    @BeforeEach
    public void setUp() {
        dish = new Dish("Pasta", "Deliciosa pasta con salsa de tomate", 12.99);
        dishReview = new DishReview("¡Sabroso!", 4.5, dish);
    }

    @Test
    public void testGetComment() {
        assertEquals("¡Sabroso!", dishReview.getComment());
    }

    @Test
    public void testGetQualification() {
        assertEquals(4.5, dishReview.getQualification());
    }

    @Test
    public void testGetDish() {
        assertEquals(dish, dishReview.getDish());
    }
}