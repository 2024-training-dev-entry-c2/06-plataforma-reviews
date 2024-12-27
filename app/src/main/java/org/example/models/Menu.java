package org.example.models;

import java.util.List;

public class Menu {
	private Integer menuId;
	private String name;
	private String description;
	private List<Dish> dishes;

	public Menu() {
	}

	public Menu(Integer menuId, String name, String description, List<Dish> dishes) {
		this.menuId = menuId;
		this.name = name;
		this.description = description;
		this.dishes = dishes;
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

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public void addDish(Dish dish) {
		dishes.add(dish);
	}

	public void updateDish(Dish dish) {
		dishes.stream()
			.filter(d -> d.getDishId().equals(dish.getDishId()))
			.findFirst()
			.ifPresent(d -> dishes.set(dishes.indexOf(d), dish));
	}

	public void deleteDish(Integer dishId) {
		dishes.removeIf(dish -> dish.getDishId().equals(dishId));
	}
}