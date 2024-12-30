package org.example.utils;

import org.example.controllers.MenuController;
import org.example.controllers.RestauranteController;
import org.example.models.Menu;
import org.example.models.Observer.Observer;
import org.example.models.Plato;
import org.example.models.Restaurante;
import org.example.models.Suscriptor;
import org.example.models.factory.Factory;
import org.example.models.factory.Review;
import org.example.models.factory.ReviewPlato;
import org.example.models.factory.ReviewPlatoFactory;
import org.example.models.factory.ReviewRestaurante;
import org.example.models.factory.ReviewRestauranteFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuApp {
    private final MenuController menuController;
    private final RestauranteController restauranteController;
    private final IManejadorConsola manejador;

    public MenuApp(MenuController menuController, RestauranteController restauranteController, IManejadorConsola manejador) {
        this.menuController = menuController;
        this.restauranteController = restauranteController;
        this.manejador = manejador;
    }


    public void displayMenu() {
        manejador.writeLine("Bienvenido al sistema de inventarios de la paletería");

        while (true) {
            manejador.writeLine("\nSeleccione una opción:");
            manejador.writeLine("1. Crear restaurante");
            manejador.writeLine("2. Editar restaurante");
            manejador.writeLine("3. Eliminar restaurante");
            manejador.writeLine("4. Listar restaurantes");
            manejador.writeLine("5. Asociar un menú a un restaurante");
            manejador.writeLine("6. Añadir plato a un menú");
            manejador.writeLine("7. Editar plato en un menú");
            manejador.writeLine("8. Eliminar plato de un menú");
            manejador.writeLine("9. Crear review para un restaurante");
            manejador.writeLine("10. Listar reviews de un restaurante");
            manejador.writeLine("11. Crear review para un plato");
            manejador.writeLine("12. Listar reviews de un plato");
            manejador.writeLine("13. Ver el menu de un restaurante");
            manejador.writeLine("14. Salir");
            manejador.writeLine("15. Eliminar menu en un restaurante");
            manejador.writeLine("16. Agregar Suscriptor a un restaurante");
            manejador.writeLine("17. eliminar Suscriptor de un restaurante");

            int option = Integer.parseInt(manejador.readLine());

            switch (option) {
                case 1 -> crearRestaurante(); //listo
                case 2 -> editarRestaurante(); //listo
                case 3 -> eliminarRestaurante(); //listo
                case 4 -> listarRestaurantes(); //listo
                case 5 -> asociarMenuARestaurante(); //listo
                case 6 -> agregarPlato(); //listo
                case 7 -> editarPlato(); //listo
                case 8 -> eliminarPlato(); //listo
                case 9 -> crearReviewRestaurante(); //listo
                case 10 -> listarReviewsRestaurante(); //listo
                case 11 -> crearReviewPlato(); //listo
                case 12 -> listarReviewsPlato(); //listo
                case 13 -> verMenu(); //listo
                case 15 -> eliminarMenu();
                case 16 -> registrarSuscriptorRestaurante();
                case 17 -> eliminarSuscriptorRestaurante();
                case 14 -> {
                    manejador.writeLine("Saliendo del sistema. ¡Gracias por preferirnos!");
                    return;
                }
                default -> manejador.writeLine("Opción no válida. Intente con un número válido.");
            }
        }
    }

    private void crearRestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombre = manejador.readLine();
        manejador.writeLine("Ingrese la direccion del restaurante:");
        String direccion = manejador.readLine();
        manejador.writeLine("Ingrese el horario del restaurante:");
        String horario = manejador.readLine();
        manejador.writeLine("Ingrese la descripcion del restaurante:");
        String descripcion = manejador.readLine();
        restauranteController.guardarRestaurante(new Restaurante(nombre, direccion, horario, descripcion));
        manejador.writeLine("Restaurante creado exitosamente.");
    }

    private void editarRestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante a editar:");
        String nombre = manejador.readLine();
        manejador.writeLine("Ingrese la nueva direccion del restaurante:");
        String direccion = manejador.readLine();
        manejador.writeLine("Ingrese el nuevo horario del restaurante:");
        String horario = manejador.readLine();
        manejador.writeLine("Ingrese la nueva descripcion del restaurante:");
        String descripcion = manejador.readLine();
        restauranteController.actualizarRestaurante(new Restaurante(nombre, direccion, horario, descripcion));
    }

    private void eliminarRestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante a eliminar:");
        String nombre = manejador.readLine();
        restauranteController.elimiarRestaurante(nombre);
    }

    private void listarRestaurantes() {
        manejador.writeLine("Lista de restaurantes:");
        restauranteController.mostrarInfo();
    }

    private void asociarMenuARestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del menú:");
        String nombreMenu = manejador.readLine();
        Menu menu = new Menu(nombreMenu);
        menuController.guardarMenu(nombreRestaurante,menu);
    }

    private void agregarPlato() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del menú al que quiere añadir un plato:");
        String nombreMenu = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del plato:");
        String nombrePlato = manejador.readLine();
        manejador.writeLine("Ingrese los ingredientes (separados por comas):");
        String ingredientes = manejador.readLine();

        Set<String> ingredientesSet = Arrays.stream(ingredientes.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        manejador.writeLine("Ingrese la descripción del plato:");
        String descripcion = manejador.readLine();
        manejador.writeLine("Ingrese el precio del plato:");
        Double precio = Double.parseDouble(manejador.readLine());
        Plato plato = new Plato(nombrePlato, ingredientesSet, descripcion, precio);
        menuController.agregarPlatoAUnMenu(nombreRestaurante,plato);
    }

    private void editarPlato() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del plato a editar:");
        String nombrePlato = manejador.readLine();
        manejador.writeLine("Ingrese los nuevos ingredientes (separados por comas):");
        String ingredientes = manejador.readLine();

        Set<String> ingredientesSet = Arrays.stream(ingredientes.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        manejador.writeLine("Ingrese la nueva descripción del plato:");
        String descripcion = manejador.readLine();
        manejador.writeLine("Ingrese el nuevo precio del plato:");
        Double precio = Double.parseDouble(manejador.readLine());
        Plato plato = new Plato(nombrePlato, ingredientesSet, descripcion, precio);
        menuController.agregarPlatoAUnMenu(nombreRestaurante,plato);
    }

    private void eliminarPlato() {
        manejador.writeLine("Ingrese el nombre del menú donde se encuentra el plato:");
        String nombreMenu = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del plato a eliminar:");
        String nombrePlato = manejador.readLine();
        menuController.eliminarPlatoDeMenu(nombreMenu,nombrePlato);
    }

    private void crearReviewRestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese la calificación (1-5):");
        Double calificacion = Double.parseDouble(manejador.readLine());
        manejador.writeLine("Ingrese el comentario:");
        String comentario = manejador.readLine();

        Factory restauranteFactory = new ReviewRestauranteFactory();
        Review reviewRestaurante = restauranteFactory.crearReview(calificacion,comentario);
        restauranteController.agregarReviewRestaurante(nombreRestaurante, (ReviewRestaurante) reviewRestaurante);
    }

    private void listarReviewsRestaurante() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        restauranteController.listarReviewsRestaurante(nombreRestaurante);
    }


    private void crearReviewPlato() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del plato:");
        String nombrePlato = manejador.readLine();
        manejador.writeLine("Ingrese la calificación (1-5):");
        Double calificacion = Double.parseDouble(manejador.readLine());
        manejador.writeLine("Ingrese el comentario:");
        String comentario = manejador.readLine();

        Factory platoFactory = new ReviewPlatoFactory();
        Review reviewPlato = platoFactory.crearReview(calificacion,comentario);
        menuController.agregarReviewPlato(nombreRestaurante,nombrePlato,(ReviewPlato) reviewPlato);
    }

    private void listarReviewsPlato() {
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del plato:");
        String nombrePlato = manejador.readLine();
        menuController.listarReviewsPlato(nombreRestaurante,nombrePlato);
    }

    private void verMenu(){
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        menuController.mostrarMenu(nombreRestaurante);
    }

    public void eliminarMenu(){
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del menú:");
        String nombreMenu = manejador.readLine();
        Menu menu = new Menu(nombreMenu);
        menuController.eliminarMenu(nombreRestaurante,menu);
    }

    public void registrarSuscriptorRestaurante(){
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del suscriptor:");
        String nombreSuscriptor = manejador.readLine();
        Observer suscriptor = new Suscriptor(nombreSuscriptor);
        restauranteController.registrarSuscriptor(nombreRestaurante,suscriptor);
    }

    public void  eliminarSuscriptorRestaurante(){
        manejador.writeLine("Ingrese el nombre del restaurante:");
        String nombreRestaurante = manejador.readLine();
        manejador.writeLine("Ingrese el nombre del suscriptor:");
        String nombreSuscriptor = manejador.readLine();
        Observer suscriptor = new Suscriptor(nombreSuscriptor);
        restauranteController.eliminarSuscriptor(nombreRestaurante,suscriptor);
    }

}



