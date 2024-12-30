package org.example.repositories;

import org.example.models.Menu;
import org.example.models.Plato;
import org.example.models.Restaurante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuRepository {

    private static MenuRepository instance;
    private Map<String, Menu> menus;


    private MenuRepository(){
        this.menus = new HashMap<>();
    }

    public static synchronized MenuRepository getInstance(){
        if (instance==null){
            instance = new MenuRepository();
        }
        return instance;
    }

    public void guardarMenu(String nombreRestaurante, Menu menu){
        menus.put(nombreRestaurante,menu);
    }

    public Boolean eliminarMenu(String nombreRestaurante, Menu menu){
        if(menus.containsKey(nombreRestaurante) && menu!=null){
            menus.remove(nombreRestaurante);
            return true;
        }
        return false;
    }

    public Boolean agregarPlatoAUnMenu(String nombreRestaurante, Plato plato) {
        Menu menu = menus.get(nombreRestaurante);
        if (menu != null) {
            menu.getPlatos().add(plato);
            notificarASuscriptores( nombreRestaurante);
            return true;
        }
        return false;
    }

    private void notificarASuscriptores(String nombreRestaurante){
        Restaurante actualRestaurante = RestaranteRepository.getInstance().buscarRestaurante(nombreRestaurante);
        if(actualRestaurante.getSuscriptores()!=null){
            actualRestaurante.notificar();
        }
    }

    public Boolean editarPlatoEnMenu(String nombreRestaurante, Plato plato) {
        Menu menu = menus.get(nombreRestaurante);
        if (menu != null) {
            ArrayList<Plato> platos = menu.getPlatos();
            if (editarMenuAux(plato, platos)) return true;
        }
        return false;
    }

    private boolean editarMenuAux(Plato plato, ArrayList<Plato> platos) {
        for (int i = 0; i < platos.size(); i++) {
            if (platos.get(i).getNombre().equals(plato.getNombre())) {
                platos.set(i, plato);
                return true;
            }
        }
        return false;
    }

    public Boolean eliminarPlatoDeMenu(String nombreRestaurante, String nombrePlato) {
        Menu menu = menus.get(nombreRestaurante);
        if (menu != null) {
            return menu.getPlatos().removeIf(plato -> plato.getNombre().equals(nombrePlato));
        }
        return false;
    }

    public void mostrarMenu(String nombreRestaurante) {
        Menu menu = menus.get(nombreRestaurante);
        System.out.println("Menú: " + menu.getNombre());
        menu.getPlatos().forEach(plato -> {
            System.out.println("- Plato: " + plato.getNombre() + ", Precio: " + plato.getPrecio());
        });
        System.out.println("-----------------------------------");
    }

    public Menu getMenu(String nombreRestaurante) {
        return menus.get(nombreRestaurante);
    }



}
