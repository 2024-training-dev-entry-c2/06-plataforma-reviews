package org.example.controllers;

import org.example.models.Plate;
import org.example.services.PlateService;

import java.util.List;

public class PlateController {
  private PlateService plateService;

  public PlateController(){
    this.plateService = new PlateService();
  }

  public PlateController(PlateService plateService){
    this.plateService = plateService;
  }

  public void addPlate(String name, String description, Double price){
    plateService.addPlate(name, description, price);
    System.out.println("Plato Creado Exitosamente");
  }

  public void getPlates(){
    List<Plate> plates = plateService.getPlates();
    if (plates.isEmpty()){
      System.out.println("No hay platos disponibles.");
    } else {
      System.out.println("Platos disponibles:");
      plates.forEach(System.out::println);
    }
  }

  public void removePlate(String name){
    plateService.removePlate(name);
    System.out.println("Plato eliminado exitosamente.");
  }
}
