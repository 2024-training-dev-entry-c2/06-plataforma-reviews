package org.example.models;

import java.util.Set;
import java.util.stream.Collectors;

public class Menu {
	private String name;
	private String description;
	private Set<Dish> dishes;

	public Menu() {
	}

	public Menu(String name, String description, Set<Dish> dishes) {
		this.name = name;
		this.description = description;
		this.dishes = dishes;
	}

	public String getName() {
		return name;
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

	public void addDish(Dish dish) {
		dishes.add(dish);
	}

	@Override
	public String toString() {
		String dishesInfo = dishes.stream().map(Object::toString).collect(Collectors.joining("\n\n"));

		return """
				Menu: '%s'
				Descripción: '%s'
				
				Platos:
				
				%s""".formatted(name, description, dishesInfo.isEmpty() ? "No hay platos en el menú" : dishesInfo);
	}
}