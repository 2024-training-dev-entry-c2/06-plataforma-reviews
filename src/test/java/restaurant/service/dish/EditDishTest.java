package restaurant.service.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DishUtils;
import restaurant.utils.RestaurantUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditDishTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private RestaurantUtils restaurantUtils;
    private DishUtils dishUtils;
    private EditDish editDish;
    private Menu menu;
    private Dish dish;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        restaurantUtils = mock(RestaurantUtils.class);
        dishUtils = mock(DishUtils.class);
        menu = mock(Menu.class);
        dish = new Dish("Pasta", "Deliciosa pasta con salsa de tomate", 12.99);

        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);
        when(menu.getDishes()).thenReturn(Arrays.asList(dish));

        editDish = new EditDish(repository, console, restaurantUtils, dishUtils);
    }

    @Test
    public void testExecuteValidDishEdit() {
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas editar: ")).thenReturn(0);
        when(console.getString("Introduce el nuevo nombre del plato: ")).thenReturn("Nueva Pasta");
        when(console.getString("Introduce la nueva descripción del plato: ")).thenReturn("Nueva descripción");
        when(console.getDouble("Introduce el nuevo precio del plato: ")).thenReturn(15.99);

        editDish.execute();

        verify(dishUtils).showDishes(menu.getDishes());
        verify(console).getString("Introduce el nuevo nombre del plato: ");
        verify(console).getString("Introduce la nueva descripción del plato: ");
        verify(console).getDouble("Introduce el nuevo precio del plato: ");
        assertEquals("Nueva Pasta", dish.getName());
        assertEquals("Nueva descripción", dish.getDescription());
        assertEquals(15.99, dish.getPrice());
    }

    @Test
    public void testExecuteMenuIsNull() {
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(null);

        editDish.execute();

        verifyNoInteractions(menu);
    }

    @Test
    public void testExecuteDishNotFound() {
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas editar: ")).thenReturn(-1);

        editDish.execute();

        verify(console, never()).getString("Introduce el nuevo nombre del plato: ");
        verify(console, never()).getString("Introduce la nueva descripción del plato: ");
        verify(console, never()).getDouble("Introduce el nuevo precio del plato: ");
    }

    @Test
    public void testExecuteWithEmptyNewName() {
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas editar: ")).thenReturn(0);
        when(console.getString("Introduce el nuevo nombre del plato: ")).thenReturn("");
        when(console.getString("Introduce la nueva descripción del plato: ")).thenReturn("Nueva descripción");
        when(console.getDouble("Introduce el nuevo precio del plato: ")).thenReturn(15.99);

        editDish.execute();

        verify(dishUtils).showDishes(menu.getDishes());
        verify(console).getString("Introduce el nuevo nombre del plato: ");
        verify(console, never()).getString("Introduce la nueva descripción del plato: ");
        verify(console, never()).getDouble("Introduce el nuevo precio del plato: ");
        assertEquals("Pasta", dish.getName());
        assertEquals("Deliciosa pasta con salsa de tomate", dish.getDescription());
        assertEquals(12.99, dish.getPrice());
    }

    @Test
    public void testExecuteWithEmptyNewDescription() {
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas editar: ")).thenReturn(0);
        when(console.getString("Introduce el nuevo nombre del plato: ")).thenReturn("Nueva Pasta");
        when(console.getString("Introduce la nueva descripción del plato: ")).thenReturn("");
        when(console.getDouble("Introduce el nuevo precio del plato: ")).thenReturn(15.99);

        editDish.execute();

        verify(dishUtils).showDishes(menu.getDishes());
        verify(console).getString("Introduce el nuevo nombre del plato: ");
        verify(console).getString("Introduce la nueva descripción del plato: ");
        verify(console, never()).getDouble("Introduce el nuevo precio del plato: ");
        assertEquals("Pasta", dish.getName());
        assertEquals("Deliciosa pasta con salsa de tomate", dish.getDescription());
        assertEquals(12.99, dish.getPrice());
    }

    @Test
    public void testExecuteWithNegativeNewPrice() {
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas editar: ")).thenReturn(0);
        when(console.getString("Introduce el nuevo nombre del plato: ")).thenReturn("Nueva Pasta");
        when(console.getString("Introduce la nueva descripción del plato: ")).thenReturn("Nueva descripción");

        when(console.getDouble("Introduce el nuevo precio del plato: ")).thenReturn(-1.0);
        editDish.execute();
        verify(dishUtils).showDishes(menu.getDishes());


        verify(console).getString("Introduce el nuevo nombre del plato: ");
        verify(console).getString("Introduce la nueva descripción del plato: ");
        verify(console).getDouble("Introduce el nuevo precio del plato: ");


        assertEquals("Pasta", dish.getName());
        assertEquals("Deliciosa pasta con salsa de tomate", dish.getDescription());
        assertEquals(12.99, dish.getPrice());
    }
}