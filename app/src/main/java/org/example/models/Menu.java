package org.example.models;

import java.util.Set;
import java.util.stream.Collectors;

public class Menu {
	private static int idCounter = 0;
	private Integer menuId;
	private String name;
	private String description;
	private Set<Dish> dishes;

	public Menu() {
	}

	public Menu(String name, String description, Set<Dish> dishes) {
		this.menuId = generateId();
		this.name = name;
		this.description = description;
		this.dishes = dishes;
	}

	private static int generateId() {
		return idCounter++;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public void addDish(Dish dish) {
		dishes.add(dish);
	}

	public void updateDish(Set<Dish> dishes, Dish updatedDish) {
		dishes.removeIf(dish -> dish.getDishId().equals(updatedDish.getDishId()));
		dishes.add(updatedDish);
	}

	public void deleteDish(Integer dishId) {
		dishes.removeIf(dish -> dish.getDishId().equals(dishId));
	}

	@Override
	public String toString() {
		return """
        Menu {
          ID: %d
          Name: '%s'
          Description: '%s'
          Platos: '%s'
        }
        """.formatted(menuId, name, description, dishes.stream().map(dish -> dish.getDishId() + ". " + dish.getName()).collect(Collectors.joining("\n")));
	}
}