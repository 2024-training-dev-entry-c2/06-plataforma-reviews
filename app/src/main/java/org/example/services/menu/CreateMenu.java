package org.example.services.menu;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommandParameterized;

public class CreateMenu implements ICommandParameterized<Menu, Restaurant> {
	@Override
	public Menu execute(Restaurant parameter) {
		return null;
	}
}
