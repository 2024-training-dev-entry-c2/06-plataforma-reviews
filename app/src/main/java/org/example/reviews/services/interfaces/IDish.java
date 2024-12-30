package org.example.reviews.services.interfaces;

import org.example.reviews.models.Dish;

import java.util.List;
import java.util.Map;

public interface IDish {
    Dish createDish();
    Map<Integer, Dish> getDishes();
    Dish updateDish();
}
