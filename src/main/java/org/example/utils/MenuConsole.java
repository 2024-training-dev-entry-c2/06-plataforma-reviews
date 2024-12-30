package org.example.utils;

import org.example.controllers.*;
import org.example.models.Plate;
import org.example.models.Restaurant;

import java.util.Scanner;

public class MenuConsole {
  private RestaurantController restaurantController;
  private MenuController menuController;
  private PlateController plateController;
  private ReviewRestaurantController reviewRestaurantController;
  private ReviewPlateController reviewPlateController;
  private Scanner scanner;

  public MenuConsole() {
    this.restaurantController = new RestaurantController();
    this.menuController = new MenuController();
    this.plateController = new PlateController();
    this.reviewRestaurantController = new ReviewRestaurantController();
    this.reviewPlateController = new ReviewPlateController();
    this.scanner = new Scanner(System.in);
  }

  public void showMainMenu(){
    int option;
    do {
      System.out.println("\n--- Menú Principal ---");
      System.out.println("1. Agregar restaurante");
      System.out.println("2. Agregar menu");
      System.out.println("3. Agregar plato");
      System.out.println("4. Agregar reseña");
      System.out.println("5. Salir");
      System.out.print("Elige una opción: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option){
        case 1 -> manageRestaurant();
        case 2 -> manageMenu();
        case 3 -> managePlate();
        case 4 -> manageReviewRestaurant();
        case 5 -> System.out.println("Saliendo...");
        default -> System.out.println("Opción inválida.");
      }
    } while (option != 5);
  }

  private void manageReviewRestaurant(){
    int option;
    do {
      System.out.println("\n---  Reseña Restaurante ---");
      System.out.println("1. Agregar reseña de restaurante");
      System.out.println("2. Listar reseñas de restaurante");
      System.out.println("3. Agregar reseña de plato");
      System.out.println("4. Listar reseñas de plato");
      System.out.println("5. Retornando al menú principal");
      System.out.print("Elige una opción: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1 -> {
          System.out.print("Ingrese el nombre del restaurante: ");
          String name = scanner.nextLine();
          Restaurant restaurant = findRestaurantByName(name);
          if (restaurant != null) {
            System.out.print("Ingrese la calificación: (1-5): ");
            Integer rating = scanner.nextInt();
            System.out.print("Ingrese el comentario: ");
            String comment = scanner.nextLine();
            reviewRestaurantController.addReview(restaurant, rating, comment);
          }
        }
        case 2 -> {
          System.out.print("Ingrese el nombre del restaurante: ");
          String name = scanner.nextLine();
          Restaurant restaurant = findRestaurantByName(name);
          if (restaurant != null) {
            reviewRestaurantController.getReviews(restaurant);
          } else {
            System.out.println("Restaurante no encontrado.");
          }
        }
        case 3 -> {
          System.out.print("Ingrese el nombre del plato: ");
          String name = scanner.nextLine();
          Plate plate = findPlateByName(name);
          if (plate != null) {
            System.out.print("Ingrese la calificación: (1-5): ");
            Integer rating = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el comentario: ");
            String comment = scanner.nextLine();
            reviewPlateController.addReview(plate, rating, comment);
          } else {
            System.out.println("Plato no encontrado.");
          }
        }
        case 4 -> {
          System.out.print("Ingrese el nombre del plato: ");
          String name = scanner.nextLine();
          Plate plate = findPlateByName(name);
          if (plate != null) {
            reviewPlateController.getReviews(plate);
          } else {
            System.out.println("Plato no encontrado.");
          }
        }
        case 5 -> System.out.println("Retornando al menú principal...");
        default -> System.out.println("Opción inválida.");
      }
    } while (option != 5);
  }

  private Restaurant findRestaurantByName(String name){
    System.out.println("Buscando restaurante con nombre: " + name);
    return new Restaurant(name, "address");
  }

  private Plate findPlateByName(String name){
    System.out.println("Buscando plato con nombre: " + name);
    return new Plate(name, "Descripción Ejemplo", 100.0);
  }

  private void manageRestaurant(){
    int option;
    do {
      System.out.println("\n--- Menú Restaurante ---");
      System.out.println("1. Agregar restaurante");
      System.out.println("2. Listar restaurantes");
      System.out.println("3. Eliminar restaurante");
      System.out.println("4. Retornando al menú principal");
      System.out.print("Elige una opción: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option){
        case 1 -> {
          System.out.print("Ingrese el nombre del restaurante: ");
          String name = scanner.nextLine();
          System.out.print("Ingrese la dirección del restaurante: ");
          String address = scanner.nextLine();
          restaurantController.addRestaurant(name, address);
        }
        case 2 -> restaurantController.getRestaurants();
        case 3 -> {
          System.out.print("Ingrese el nombre del restaurante a eliminar: ");
          String name = scanner.nextLine();
          restaurantController.removeRestaurant(name);
        }
        case 4 -> System.out.println("Retornando al menú principal...");
        default -> System.out.println("Opción inválida.");
      }
    } while (option != 4);
  }

  private void manageMenu() {
    int option;
    do {
      System.out.println("\n--- Menú Menu ---");
      System.out.println("1. Agregar menu");
      System.out.println("2. Listar menus");
      System.out.println("3. Eliminar menu");
      System.out.println("4. Agregar plato al menu");
      System.out.println("5. Retornando al menú principal");
      System.out.print("Elige una opción: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1 -> {
          System.out.print("Ingrese el nombre del menu: ");
          String name = scanner.nextLine();
          menuController.addMenu(name);
        }
        case 2 -> menuController.getMenus();
        case 3 -> {
          System.out.print("Ingrese el nombre del menu a eliminar: ");
          String name = scanner.nextLine();
          menuController.removeMenu(name);
        }
        case 4 -> {
          System.out.print("Ingrese el nombre del menu: ");
          String nameMenu = scanner.nextLine();
          System.out.print("Ingrese el nombre del plato: ");
          String namePlate = scanner.nextLine();
          System.out.print("Ingrese la descripción del plato: ");
          String description = scanner.nextLine();
          System.out.print("Ingrese el precio del plato: ");
          Double price = scanner.nextDouble();
          scanner.nextLine();
          Plate plate = new Plate(namePlate, description, price);
          menuController.addPlateToMenu(nameMenu, plate);
        }
        case 5 -> System.out.println("Retornando al menú principal...");
        default -> System.out.println("Opción inválida.");
      }
    } while (option != 5);
  }

  private void managePlate() {
    int option;
    do {
      System.out.println("\n--- Menú Plato ---");
      System.out.println("1. Agregar plato");
      System.out.println("2. Listar platos");
      System.out.println("3. Eliminar plato");
      System.out.println("4. Retornando al menú principal");
      System.out.print("Elige una opción: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1 -> {
          System.out.print("Ingrese el nombre del plato: ");
          String name = scanner.nextLine();
          System.out.print("Ingrese la descripción del plato: ");
          String description = scanner.nextLine();
          System.out.print("Ingrese el precio del plato: ");
          Double price = scanner.nextDouble();
          scanner.nextLine();
          plateController.addPlate(name, description, price);
        }
        case 2 -> plateController.getPlates();
        case 3 -> {
          System.out.print("Ingrese el nombre del plato a eliminar: ");
          String name = scanner.nextLine();
          plateController.removePlate(name);
        }
        case 4 -> System.out.println("Retornando al menú principal...");
        default -> System.out.println("Opción inválida.");
      }
    } while (option != 4);
  }
}
