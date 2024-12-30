
package org.example;


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
import org.example.models.factory.ReviewRestaurante;
import org.example.models.factory.ReviewRestauranteFactory;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaranteRepository;
import org.example.utils.IManejadorConsola;
import org.example.utils.ManejadorConsola;
import org.example.utils.MenuApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppTest {

    private IManejadorConsola mockManejador;
    private RestauranteController mockRestauranteController;
    private MenuController mockMenuController;
    private MenuApp menuApp;
    private Restaurante mockRestaurante;

    //REALES
    private RestauranteController restauranteController;
    private MenuController menuController;
    private IManejadorConsola manejador;

    @BeforeEach
    void setup(){
        mockManejador = mock(IManejadorConsola.class);
        mockRestauranteController = mock(RestauranteController.class);
        mockMenuController = mock(MenuController.class);
        menuApp = new MenuApp(mockMenuController, mockRestauranteController, mockManejador);
        mockRestaurante = mock(Restaurante.class);

        restauranteController = new RestauranteController();
        menuController = new MenuController();

        manejador = new ManejadorConsola();
    }

    @Test
    @DisplayName("Caso 1 registrar restaurante")
    void testAgregarRestaurante(){
        when(mockManejador.readLine())
                .thenReturn("1", "los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad","14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese la direccion del restaurante:");
        verify(mockManejador).writeLine("Ingrese el horario del restaurante:");
        verify(mockManejador).writeLine("Ingrese la descripcion del restaurante:");


        restauranteController.guardarRestaurante(new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad"));
        Restaurante restaurante = RestaranteRepository.getInstance().buscarRestaurante("los tizones");
        assertNotNull(restaurante);
        assertEquals("los tizones", restaurante.getNombre());
        assertEquals("CR 41 #54a-54", restaurante.getDireccion());
        assertEquals("8:00AM/9:00PM", restaurante.getHorario());
        assertEquals("comidas y asados de gran calidad", restaurante.getDescripcion());

    }

    @Test
    @DisplayName("Caso 2 actualizar restaurante")
    void testActualizarRestaurante(){
        when(mockManejador.readLine())
                .thenReturn("2", "los tizones", "San Roque", "9:00AM/10:00PM", "comidas y asados de gran calidad","14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante a editar:");
        verify(mockManejador).writeLine("Ingrese la nueva direccion del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nuevo horario del restaurante:");
        verify(mockManejador).writeLine("Ingrese la nueva descripcion del restaurante:");


        restauranteController.guardarRestaurante(new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad"));

        restauranteController.actualizarRestaurante(new Restaurante("los tizones", "San Roque", "9:00AM/10:00PM", "comidas y asados de gran calidad"));

        Restaurante restaurante = RestaranteRepository.getInstance().buscarRestaurante("los tizones");
        assertNotNull(restaurante);
        assertEquals("los tizones", restaurante.getNombre());
        assertEquals("San Roque", restaurante.getDireccion());
        assertEquals("9:00AM/10:00PM", restaurante.getHorario());
        assertEquals("comidas y asados de gran calidad", restaurante.getDescripcion());

    }


    @Test
    @DisplayName("Caso 3 eliminar restaurante")
    void testEliminarRestaurante() {

        when(mockManejador.readLine())
                .thenReturn("3", "los tizones", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante a eliminar:");

        restauranteController.guardarRestaurante(new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad"));
        restauranteController.elimiarRestaurante("los tizones");
        Restaurante restaurante = RestaranteRepository.getInstance().buscarRestaurante("los tizones");
        assertNull(restaurante, "Restaurante eliminado con exito");

    }

    @Test
    @DisplayName("Caso 4 listar restaurantes")
    void testListarRestaurantes() {

        when(mockManejador.readLine())
                .thenReturn("4", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Lista de restaurantes:");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            restauranteController.guardarRestaurante(
                    new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad")
            );

            restauranteController.mostrarInfo();

            String output = outputStream.toString();

            assertTrue(output.contains("Nombre: los tizones"), "El nombre del restaurante no coincide.");
            assertTrue(output.contains("Dirección: CR 41 #54a-54"), "La dirección del restaurante no coincide.");
            assertTrue(output.contains("Horario: 8:00AM/9:00PM"), "El horario del restaurante no coincide.");
            assertTrue(output.contains("Descripción: comidas y asados de gran calidad"), "La descripción del restaurante no coincide.");
            assertTrue(output.contains("-----------------------------------"), "El separador no está presente.");

        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    @DisplayName("Caso 5 asociar menú a restaurante")
    void testAsociarMenuARestaurante() {

        when(mockManejador.readLine())
                .thenReturn("5", "los tizones", "menu del dia", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del menú:");

        Menu expectedMenu = new Menu("menu del dia");

        menuController.guardarMenu("los tizones", expectedMenu);

        Menu menu = MenuRepository.getInstance().getMenu("los tizones");
        assertNotNull(menu);
        assertEquals("menu del dia", menu.getNombre());
    }

    @Test
    @DisplayName("Caso 6 añadir plato a un menú")
    void testAgregarPlato() {

        when(mockManejador.readLine())
                .thenReturn(
                        "6",
                        "loz tizones",
                        "menu del día",
                        "Plato Especial",
                        "pollo, arroz, guisantes",
                        "Delicioso plato con sabor único",
                        "15.5",
                        "14"
                );

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del menú al que quiere añadir un plato:");
        verify(mockManejador).writeLine("Ingrese el nombre del plato:");
        verify(mockManejador).writeLine("Ingrese los ingredientes (separados por comas):");
        verify(mockManejador).writeLine("Ingrese la descripción del plato:");
        verify(mockManejador).writeLine("Ingrese el precio del plato:");

        Set<String> ingredientesEsperados = Set.of("pollo", "arroz", "guisantes");


        Plato platoEsperado = new Plato(
                "Plato Especial",
                ingredientesEsperados,
                "Delicioso plato con sabor único",
                15.5
        );

        restauranteController.guardarRestaurante(
                new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad")
        );
        Menu expectedMenu = new Menu("menu del dia");
        menuController.guardarMenu("los tizones", expectedMenu);
        menuController.agregarPlatoAUnMenu("los tizones",platoEsperado);

        Plato platoEncontrado = expectedMenu.getPlatos().stream()
                .filter(plato -> plato.getNombre().equals("Plato Especial"))
                .findFirst()
                .orElse(null);


        assertNotNull(platoEncontrado, "El plato debería existir en el menú");
        assertEquals("Plato Especial", platoEncontrado.getNombre(), "El nombre del plato no es correcto");
        assertEquals(ingredientesEsperados, platoEncontrado.getIngredientes(), "Los ingredientes del plato no son correctos");
        assertEquals("Delicioso plato con sabor único", platoEncontrado.getDescripcion(), "La descripción del plato no es correcta");
        assertEquals(15.5, platoEncontrado.getPrecio(), "El precio del plato no es correcto");

    }

    @Test
    @DisplayName("Caso 7 editar plato a un menú")
    void editarPlato() {

        when(mockManejador.readLine())
                .thenReturn(
                        "7",
                        "loz tizones",
                        "Plato Especial",
                        "pollo, arroz, lentejas",
                        "plato maluquito que quita el hambre",
                        "3.2",
                        "14"
                );

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del plato a editar:");
        verify(mockManejador).writeLine("Ingrese los nuevos ingredientes (separados por comas):");
        verify(mockManejador).writeLine("Ingrese la nueva descripción del plato:");
        verify(mockManejador).writeLine("Ingrese el nuevo precio del plato:");


        Set<String> ingredientesEsperados = Set.of("pollo", "arroz", "guisantes");


        Plato platoEsperado = new Plato(
                "Plato Especial",
                ingredientesEsperados,
                "Delicioso plato con sabor único",
                15.5
        );

        restauranteController.guardarRestaurante(
                new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad")
        );
        Menu expectedMenu = new Menu("menu del dia");
        menuController.guardarMenu("los tizones", expectedMenu);
        menuController.agregarPlatoAUnMenu("los tizones",platoEsperado);


        Set<String> ingredientesActualizados = Set.of("pollo", "arroz", "lentejas");


        menuController.editarPlatoEnMenu("los tizones",new Plato("Plato Especial",
                ingredientesActualizados,
                "plato maluquito que quita el hambre",
                3.2));


        Plato platoEncontrado = expectedMenu.getPlatos().stream()
                .filter(plato -> plato.getNombre().equals("Plato Especial"))
                .findFirst()
                .orElse(null);

        assertNotNull(platoEncontrado, "El plato debería existir en el menú");
        assertEquals("Plato Especial", platoEncontrado.getNombre(), "El nombre del plato no es correcto");
        assertEquals(ingredientesActualizados, platoEncontrado.getIngredientes(), "Los ingredientes del plato no son correctos");
        assertEquals("plato maluquito que quita el hambre", platoEncontrado.getDescripcion(), "La descripción del plato no es correcta");
        assertEquals(3.2, platoEncontrado.getPrecio(), "El precio del plato no es correcto");

    }


    @Test
    @DisplayName("Caso 8 eliminar plato de un menú")
    void testEliminarPlato() {

        when(mockManejador.readLine())
                .thenReturn(
                        "8",
                        "menú del día",
                        "Plato Especial",
                        "14"
                );

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del menú donde se encuentra el plato:");
        verify(mockManejador).writeLine("Ingrese el nombre del plato a eliminar:");

        verify(mockMenuController).eliminarPlatoDeMenu("menú del día", "Plato Especial");

        Set<String> ingredientesEsperados = Set.of("pollo", "arroz", "guisantes");
        Plato platoEsperado = new Plato(
                "Plato Especial",
                ingredientesEsperados,
                "Delicioso plato con sabor único",
                15.5
        );

        restauranteController.guardarRestaurante(
                new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad")
        );
        Menu expectedMenu = new Menu("menu del dia");
        menuController.guardarMenu("los tizones", expectedMenu);
        menuController.agregarPlatoAUnMenu("los tizones",platoEsperado);

        menuController.eliminarPlatoDeMenu("los tizones", "Plato Especial");

        Menu menuGuardado = menuController.getMenu("los tizones");
        assertFalse(menuGuardado.getPlatos().contains(platoEsperado), "El plato debería haber sido eliminado del menú");

    }

    @Test
    @DisplayName("Caso 9 crear review para un restaurante")
    void testCrearReviewRestaurante() {

        when(mockManejador.readLine())
                .thenReturn("9", "los tizones", "4.5", "Excelente servicio y comida deliciosa", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese la calificación (1-5):");
        verify(mockManejador).writeLine("Ingrese el comentario:");


        Factory restauranteFactory = new ReviewRestauranteFactory();
        Review expectedReview = restauranteFactory.crearReview(4.5, "Excelente servicio y comida deliciosa");

        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "Comidas y asados de gran calidad");
        restauranteController.guardarRestaurante(restaurante);
        restauranteController.agregarReviewRestaurante("los tizones", (ReviewRestaurante) expectedReview);

        Restaurante actualRestaurante = RestaranteRepository.getInstance().buscarRestaurante("los tizones");
        assertNotNull(actualRestaurante, "El restaurante no debería ser nulo");

        assertTrue(actualRestaurante.getReviews().contains(expectedReview), "El review no fue agregado correctamente");

        ReviewRestaurante addedReview = (ReviewRestaurante) actualRestaurante.getReviews().stream()
                .filter(review -> review.equals(expectedReview))
                .findFirst()
                .orElse(null);

        assertNotNull(addedReview, "El review no se encontró en la lista de reviews del restaurante");
        assertEquals(4.5, addedReview.getCalificacion(), "La calificación del review no es correcta");
        assertEquals("Excelente servicio y comida deliciosa", addedReview.getComentario(), "El comentario del review no es correcto");

    }

    @Test
    @DisplayName("Caso 10 listar reviews de un restaurante")
    void testListarReviewsRestaurante() {

        when(mockManejador.readLine())
                .thenReturn("10",
                        "los tizones",
                        "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");

        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "Comidas y asados de gran calidad");
        restauranteController.guardarRestaurante(restaurante);

        ReviewRestaurante review1 = new ReviewRestaurante(4.5, "Excelente servicio y comida deliciosa");
        ReviewRestaurante review2 = new ReviewRestaurante(3.0, "Comida buena, pero el servicio mejorable");
        restauranteController.agregarReviewRestaurante("los tizones", review1);
        restauranteController.agregarReviewRestaurante("los tizones", review2);

        restauranteController.listarReviewsRestaurante("los tizones");

        Restaurante actualRestaurante = RestaranteRepository.getInstance().buscarRestaurante("los tizones");
        assertNotNull(actualRestaurante, "El restaurante no debería ser nulo");


        assertTrue(actualRestaurante.getReviews().contains(review1), "El review1 no se encuentra en la lista de reviews");
        assertTrue(actualRestaurante.getReviews().contains(review2), "El review2 no se encuentra en la lista de reviews");

        assertEquals(4.5, review1.getCalificacion(), "La calificación de review1 no es correcta");
        assertEquals("Excelente servicio y comida deliciosa", review1.getComentario(), "El comentario de review1 no es correcto");

        assertEquals(3.0, review2.getCalificacion(), "La calificación de review2 no es correcta");
        assertEquals("Comida buena, pero el servicio mejorable", review2.getComentario(), "El comentario de review2 no es correcto");
    }


    @Test
    @DisplayName("Caso 11 crear review para un plato")
    void testCrearReviewPlato() {


        when(mockManejador.readLine())
                .thenReturn(
                        "11",
                        "los tizones",
                        "Plato Especial",
                        "4.5",
                        "Delicioso plato, bien preparado",
                        "14"
                );


        menuApp.displayMenu();


        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del plato:");
        verify(mockManejador).writeLine("Ingrese la calificación (1-5):");
        verify(mockManejador).writeLine("Ingrese el comentario:");


        ReviewPlato expectedReview = new ReviewPlato(4.5, "Delicioso plato, bien preparado");

        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad");
        restauranteController.guardarRestaurante(restaurante);

        Menu menu = new Menu("menu del día");
        menuController.guardarMenu("los tizones", menu);

        Set<String> ingredientes = Set.of("pollo", "arroz", "guisantes");
        Plato plato = new Plato("Plato Especial", ingredientes, "Delicioso plato con sabor único", 15.5);
        menuController.agregarPlatoAUnMenu("los tizones", plato);

        menuController.agregarReviewPlato("los tizones", "Plato Especial", expectedReview);

        Menu menuActual = menuController.getMenu("los tizones");
        assertNotNull(menuActual, "El menú del restaurante debería existir");

        Plato platoEncontrado = menuActual.getPlatos().stream()
                .filter(p -> p.getNombre().equals("Plato Especial"))
                .findFirst()
                .orElse(null);

        assertNotNull(platoEncontrado, "El plato debería existir en el menú");

        assertTrue(platoEncontrado.getReviews().contains(expectedReview), "La reseña no fue agregada al plato correctamente");

        ReviewPlato reviewEncontrada = platoEncontrado.getReviews().stream()
                .filter(review -> review.getComentario().equals("Delicioso plato, bien preparado"))
                .findFirst()
                .orElse(null);

        assertNotNull(reviewEncontrada, "La reseña no se encontró en la lista de reseñas del plato");
        assertEquals(4.5, reviewEncontrada.getCalificacion(), "La calificación de la reseña no es correcta");
        assertEquals("Delicioso plato, bien preparado", reviewEncontrada.getComentario(), "El comentario de la reseña no es correcto");
    }


    @Test
    @DisplayName("Caso 12 listar reviews de un plato")
    void testListarReviewsPlato() {


        when(mockManejador.readLine())
                .thenReturn(
                        "12",
                        "los tizones",
                        "Plato Especial",
                        "14"
                );

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del plato:");


        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "Comidas y asados de gran calidad");
        restauranteController.guardarRestaurante(restaurante);

        Menu menu = new Menu("Menú del Día");
        menuController.guardarMenu("los tizones", menu);

        Set<String> ingredientes = Set.of("pollo", "arroz", "guisantes");
        Plato plato = new Plato("Plato Especial", ingredientes, "Plato delicioso", 15.5);
        menuController.agregarPlatoAUnMenu("los tizones", plato);


        ReviewPlato review1 = new ReviewPlato(4.8, "Sabor espectacular, presentación impecable");
        ReviewPlato review2 = new ReviewPlato(4.0, "Buen sabor, pero porciones pequeñas");
        menuController.agregarReviewPlato("los tizones", "Plato Especial", review1);
        menuController.agregarReviewPlato("los tizones", "Plato Especial", review2);


        menuController.listarReviewsPlato("los tizones", "Plato Especial");


        Menu actualMenu = menuController.getMenu("los tizones");
        assertNotNull(actualMenu, "El menú no debería ser nulo");

        Plato actualPlato = actualMenu.getPlatos().stream()
                .filter(p -> p.getNombre().equals("Plato Especial"))
                .findFirst()
                .orElse(null);

        assertNotNull(actualPlato, "El plato no debería ser nulo");
        assertTrue(actualPlato.getReviews().contains(review1), "El review1 no se encuentra en el plato");
        assertTrue(actualPlato.getReviews().contains(review2), "El review2 no se encuentra en el plato");

        assertEquals(4.8, review1.getCalificacion(), "La calificación de review1 no es correcta");
        assertEquals("Sabor espectacular, presentación impecable", review1.getComentario(), "El comentario de review1 no es correcto");

        assertEquals(4.0, review2.getCalificacion(), "La calificación de review2 no es correcta");
        assertEquals("Buen sabor, pero porciones pequeñas", review2.getComentario(), "El comentario de review2 no es correcto");
    }


    @Test
    @DisplayName("Caso 13 ver menú de un restaurante")
    void testVerMenu() {

        when(mockManejador.readLine())
                .thenReturn(
                        "13",
                        "los tizones",
                        "14"
                );

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");

        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad");
        restauranteController.guardarRestaurante(restaurante);

        Menu menu = new Menu("Menú del Día");
        Set<String> ingredientes = Set.of("pollo", "arroz", "guisantes");
        Plato plato1 = new Plato("Plato Especial", ingredientes, "Delicioso plato con sabor único", 15.5);
        Plato plato2 = new Plato("Plato Vegetariano", ingredientes, "Sano y sabroso", 12.0);

        menuController.guardarMenu("los tizones", menu);
        menuController.agregarPlatoAUnMenu("los tizones", plato1);
        menuController.agregarPlatoAUnMenu("los tizones", plato2);

        menuController.mostrarMenu("los tizones");
    }


    @Test
    @DisplayName("Caso 15 eliminar menu")
    void eliminarMenu() {

        when(mockManejador.readLine())
                .thenReturn("15", "los tizones", "menu del dia", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del menú:");

        Menu expectedMenu = new Menu("menu del dia");

        menuController.guardarMenu("los tizones", expectedMenu);

        Menu menu = MenuRepository.getInstance().getMenu("los tizones");
        assertNotNull(menu);
        assertEquals("menu del dia", menu.getNombre());

        menuController.eliminarMenu("los tizones", expectedMenu);
        Menu menuEliminado = MenuRepository.getInstance().getMenu("los tizones");
        assertEquals(null,menuEliminado,"El menu no se elimino");

    }

    @Test
    @DisplayName("Caso 16 registrar suscriptor")
    void registrarSuscriptorTest() {

        when(mockManejador.readLine())
                .thenReturn("16", "los tizones", "carlos", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del suscriptor:");

        restauranteController.guardarRestaurante(new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad"));

        Observer suscriptor = new Suscriptor("carlos");

        restauranteController.registrarSuscriptor("los tizones",suscriptor);

    }

    @Test
    @DisplayName("Caso 17 eliminar suscriptor")
    void eliminarSuscriptorTest() {

        when(mockManejador.readLine())
                .thenReturn("17", "los tizones", "carlos", "14");

        menuApp.displayMenu();

        verify(mockManejador).writeLine("Ingrese el nombre del restaurante:");
        verify(mockManejador).writeLine("Ingrese el nombre del suscriptor:");

        restauranteController.guardarRestaurante(new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "comidas y asados de gran calidad"));

        Observer suscriptor = new Suscriptor("carlos");

        restauranteController.registrarSuscriptor("los tizones",suscriptor);

        restauranteController.eliminarSuscriptor("los tizones",suscriptor);

    }


    @Test
    @DisplayName("Caso 18 notificar a suscriptores al agregar un plato")
    void testNotificarASuscriptores() {
       
        Restaurante restaurante = new Restaurante("los tizones", "CR 41 #54a-54", "8:00AM/9:00PM", "Comidas y asados de gran calidad");
        Suscriptor suscriptor1 = new Suscriptor("Juan");
        Suscriptor suscriptor2 = new Suscriptor("Maria");


        restauranteController.guardarRestaurante(restaurante);

        restauranteController.registrarSuscriptor("los tizones",suscriptor1);
        restauranteController.registrarSuscriptor("los tizones",suscriptor2);

        Menu menu = new Menu("menu del día");
        menuController.guardarMenu("los tizones", menu);


        Plato plato = new Plato(
                "Plato Especial",
                Set.of("pollo", "arroz", "guisantes"),
                "Delicioso plato con sabor único",
                15.5
        );


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menuController.agregarPlatoAUnMenu("los tizones", plato);

        System.setOut(System.out);

        String output = outContent.toString();
        String mensajeEsperado1 = "Hola Juan El restaurante ha agregado un nuevo plato!, echale un vistazo";
        String mensajeEsperado2 = "Hola Maria El restaurante ha agregado un nuevo plato!, echale un vistazo";

        assertTrue(output.contains(mensajeEsperado1), "No se encontró el mensaje esperado para Juan");
        assertTrue(output.contains(mensajeEsperado2), "No se encontró el mensaje esperado para María");

        Plato platoEncontrado = menu.getPlatos().stream()
                .filter(p -> p.getNombre().equals("Plato Especial"))
                .findFirst()
                .orElse(null);

        assertNotNull(platoEncontrado, "El plato debería existir en el menú");
        assertEquals("Plato Especial", platoEncontrado.getNombre(), "El nombre del plato no es correcto");
        assertEquals(Set.of("pollo", "arroz", "guisantes"), platoEncontrado.getIngredientes(), "Los ingredientes del plato no son correctos");
        assertEquals("Delicioso plato con sabor único", platoEncontrado.getDescripcion(), "La descripción del plato no es correcta");
        assertEquals(15.5, platoEncontrado.getPrecio(), "El precio del plato no es correcto");
    }


    @Test
    @DisplayName("Caso 18 test consola")
    void testConsola() {

        String input = "Entrada de prueba\n";
        InputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ManejadorConsola manejador = new ManejadorConsola();

        manejador.writeLine("Escribe algo para el test");
        String mensaje = manejador.readLine();
        manejador.writeLine(mensaje);

        String[] lines = outContent.toString().split(System.lineSeparator());
        assertEquals("Escribe algo para el test", lines[0], "El mensaje inicial no coincide.");
        assertEquals("Entrada de prueba", lines[1], "El mensaje leído no coincide con el esperado.");

        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    @DisplayName("Caso 19 App test")
    void AppTest() {

        String input = "14\n" +
                "14\n";
        InputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        try {
            App.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
            fail("El programa no se ejecutó correctamente: " + e.getMessage());
        }

        String salida = outContent.toString();
        assertTrue(salida.contains("Saliendo del sistema"), "El mensaje de salida no es el esperado.");

        System.setIn(System.in);
        System.setOut(System.out);
    }














}
