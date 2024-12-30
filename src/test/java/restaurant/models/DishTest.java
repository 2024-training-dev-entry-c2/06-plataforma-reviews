package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.Interface.IReview;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DishTest {
    private Dish dish;

    @BeforeEach
    public void setUp() {
        dish = new Dish("Pasta", "Deliciosa pasta con salsa de tomate", 12.99);
    }

    @Test
    public void testAddReview() {
        IReview review = new DishReview("¡Sabroso!", 4.5, dish);
        dish.addReview(review);

        assertEquals(4.5, dish.getAverageRating());
        assertTrue(dish.getReviews().contains(review));
    }

    @Test
    public void testSetName() {
        dish.setName("Rabioles");

        assertEquals("Rabioles", dish.getName());
    }

    @Test
    public void testSetDescription() {
        dish.setDescription("Rabioles con albondiga");

        assertEquals("Rabioles con albondiga", dish.getDescription());
    }

    @Test
    public void testSetPrice() {
        dish.setPrice(15.99);

        assertEquals(15.99, dish.getPrice());
    }

    @Test
    public void testSetReviews() {
        IReview review1 = new DishReview("Buena", 4.0, dish);
        IReview review2 = new DishReview("Excelente servicio", 5.0, dish);
        List<IReview> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        dish.setReviews(reviews);

        assertEquals(reviews, dish.getReviews());
    }

    @Test
    public void testSetAverageRating() {
        dish.setAverageRating(4.8);

        assertEquals(4.8, dish.getAverageRating());
    }

    @Test
    public void testGetName() {
        assertEquals("Pasta", dish.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Deliciosa pasta con salsa de tomate", dish.getDescription());
    }

    @Test
    public void testGetPrice() {
        assertEquals(12.99, dish.getPrice());
    }

    @Test
    public void testGetReviews() {
        assertTrue(dish.getReviews().isEmpty());
    }

    @Test
    public void testGetAverageRating() {
        assertEquals(0.0, dish.getAverageRating());
    }
}