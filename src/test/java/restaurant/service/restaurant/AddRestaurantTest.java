package restaurant.service.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DefaultMenu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddRestaurantTest {
    private AddRestaurant addRestaurant;
    private RestaurantRepository repository;
    private ConsoleUtils console;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        addRestaurant = new AddRestaurant(repository, console);
    }

    @Test
    public void testExecute() {
        when(console.getString("Ingrese el nombre del restaurante: ")).thenReturn("Test Restaurant");
        when(console.getString("Ingrese la dirección del restaurante: ")).thenReturn("Test Address");

        Restaurant restaurant = addRestaurant.execute();

        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals("Test Address", restaurant.getAddress());

        Menu menu = restaurant.getMenu();
        assertNotNull(menu);
        assertEquals(3, menu.getDishes().size());

        Dish dish1 = menu.getDishes().get(0);
        assertEquals("Plato 1", dish1.getName());
        assertEquals("Descripción del Plato 1", dish1.getDescription());
        assertEquals(10.0, dish1.getPrice());

        Dish dish2 = menu.getDishes().get(1);
        assertEquals("Plato 2", dish2.getName());
        assertEquals("Descripción del Plato 2", dish2.getDescription());
        assertEquals(15.0, dish2.getPrice());

        Dish dish3 = menu.getDishes().get(2);
        assertEquals("Plato 3", dish3.getName());
        assertEquals("Descripción del Plato 3", dish3.getDescription());
        assertEquals(20.0, dish3.getPrice());

        verify(repository).addRestaurant(restaurant);
        verify(repository, times(1)).addRestaurant(restaurant);
    }
}