package restaurant.utils;

import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;

public class SeedData {
    public static void preloadData(RestaurantRepository repository) {
        // Create restaurants
        Restaurant restaurant1 = new Restaurant("Restaurante Azeki", "Address A");
        Restaurant restaurant2 = new Restaurant("Restaurante Bollero", "Madagascar");
        Restaurant restaurant3 = new Restaurant("Restaurante Cazona", "Babilonia");

        // Create menus
        Menu menu1 = new Menu(restaurant1);
        Menu menu2 = new Menu(restaurant2);
        Menu menu3 = new Menu(restaurant3);

        // Create dishes for restaurant 1
        menu1.addDish(new Dish("Chivito", "Sándwich tradicional uruguayo con carne de res, jamón, queso y vegetales", 10.0));
        menu1.addDish(new Dish("Asado", "Costillas de res a la parrilla, un plato básico de la cocina uruguaya", 15.0));
        menu1.addDish(new Dish("Milanesa", "Chuleta de carne empanizada y frita, a menudo servida con papas fritas", 12.0));
        menu1.addDish(new Dish("Empanadas", "Masa rellena de carne, queso u otros ingredientes", 8.0));
        menu1.addDish(new Dish("Pizza a la Parrilla", "Pizza a la parrilla con una variedad de ingredientes", 9.0));

        // Create dishes for restaurant 2
        menu2.addDish(new Dish("Choripán", "Chorizo a la parrilla en un pan, a menudo servido con chimichurri", 11.0));
        menu2.addDish(new Dish("Fainá", "Pan plano de harina de garbanzo, a menudo servido con pizza", 6.0));
        menu2.addDish(new Dish("Tortas Fritas", "Masa frita, típicamente disfrutada con mate", 5.0));
        menu2.addDish(new Dish("Pasta con Tuco", "Pasta con una rica salsa de tomate y carne", 13.0));
        menu2.addDish(new Dish("Polenta", "Plato de harina de maíz a menudo servido con queso y salsa", 10.0));

        // Create dishes for restaurant 3
        menu3.addDish(new Dish("Cazuela de Mariscos", "Estofado de mariscos con una variedad de mariscos y pescado", 17.0));
        menu3.addDish(new Dish("Arroz con Leche", "Postre cremoso de arroz con leche", 7.0));
        menu3.addDish(new Dish("Bizcochos", "Variedad de pasteles, a menudo disfrutados con café o mate", 4.0));
        menu3.addDish(new Dish("Matambre a la Pizza", "Corte delgado de carne cubierto con ingredientes de pizza", 14.0));
        menu3.addDish(new Dish("Ensalada Rusa", "Ensalada de papas con arvejas, zanahorias y mayonesa", 8.0));

        // Set menus to restaurants
        restaurant1.setMenu(menu1);
        restaurant2.setMenu(menu2);
        restaurant3.setMenu(menu3);

        // Add restaurants to repository
        repository.addRestaurant(restaurant1);
        repository.addRestaurant(restaurant2);
        repository.addRestaurant(restaurant3);
    }
}