package restaurant.service.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;

import static org.mockito.Mockito.*;

public class AddDishTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private RestaurantUtils restaurantUtils;
    private AddDish addDish;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        restaurantUtils = mock(RestaurantUtils.class);
        menu = mock(Menu.class);

        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);

        addDish = new AddDish(repository, console, restaurantUtils);
    }

    @Test
    public void testExecuteValidDish() {
        when(console.getString("Introduce el nombre del plato: ")).thenReturn("Pasta");
        when(console.getString("Introduce la descripción del plato: ")).thenReturn("Deliciosa pasta con salsa de tomate");
        when(console.getDouble("Introduce el precio del plato: ")).thenReturn(12.99);

        addDish.execute();

        verify(menu).addDish(argThat(dish ->
                dish.getName().equals("Pasta") &&
                        dish.getDescription().equals("Deliciosa pasta con salsa de tomate") &&
                        dish.getPrice() == 12.99
        ));
        verify(console).getString("Introduce el nombre del plato: ");
        verify(console).getString("Introduce la descripción del plato: ");
        verify(console).getDouble("Introduce el precio del plato: ");
    }

    @Test
    public void testExecuteMenuIsNull() {
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(null);

        addDish.execute();

        verify(menu, never()).addDish(any(Dish.class));
    }

    @Test
    public void testExecuteHandlesEmptyMenuGracefully() {
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(null);

        addDish.execute();

        verifyNoInteractions(menu);
    }

    @Test
    public void testExecuteWithEmptyName() {
        // Simulamos que el nombre es vacío
        when(console.getString("Introduce el nombre del plato: ")).thenReturn("");
        when(console.getString("Introduce la descripción del plato: ")).thenReturn("Deliciosa pasta con salsa de tomate");
        when(console.getDouble("Introduce el precio del plato: ")).thenReturn(12.99);

        addDish.execute();
        verify(menu, never()).addDish(any(Dish.class));

        verify(console).getString("Introduce el nombre del plato: ");
        verify(console, never()).getString("Introduce la descripción del plato: ");
        verify(console, never()).getDouble("Introduce el precio del plato: ");
    }

    @Test
    public void testExecuteWithEmptyDescription() {
        when(console.getString("Introduce el nombre del plato: ")).thenReturn("Pasta");
        when(console.getString("Introduce la descripción del plato: ")).thenReturn("");
        when(console.getDouble("Introduce el precio del plato: ")).thenReturn(12.99);

        addDish.execute();

        verify(menu, never()).addDish(any(Dish.class));

        verify(console).getString("Introduce el nombre del plato: ");
        verify(console).getString("Introduce la descripción del plato: ");
        verify(console, never()).getDouble("Introduce el precio del plato: ");
    }

    @Test
    public void testExecuteWithNegativePrice() {
        when(console.getString("Introduce el nombre del plato: ")).thenReturn("Pasta");
        when(console.getString("Introduce la descripción del plato: ")).thenReturn("Deliciosa pasta con salsa de tomate");
        when(console.getDouble("Introduce el precio del plato: ")).thenReturn(-1.0);

        addDish.execute();

        verify(menu, never()).addDish(any(Dish.class));

        verify(console).getString("Introduce el nombre del plato: ");
        verify(console).getString("Introduce la descripción del plato: ");
        verify(console).getDouble("Introduce el precio del plato: ");
    }
}