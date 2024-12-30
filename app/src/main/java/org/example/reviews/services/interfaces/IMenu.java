package org.example.reviews.services.interfaces;

import org.example.reviews.models.Menu;

import java.util.List;
import java.util.Map;

public interface IMenu {
    Menu createMenu();
    List<Menu> displayMenus();
}
