package org.example.models;

import org.example.models.interfaces.Observable;

import java.util.HashMap;

import java.util.Map;

public class Restaurant extends Observable {

    private String name;
    private String address;
    private Menu menu;
    private Map<User, ReviewRestaurant> reviewMap;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.reviewMap = new HashMap<>();
    }

    public void addReview(User user ,IReview review) {
        reviewMap.put(user, (ReviewRestaurant) review);
        notifyObservers("mensaje pronto");//mensaje del review
    }

    public void getAverageRating(){
        double suma = 0;
        for (ReviewRestaurant review : reviewMap.values()) {
            suma += (review.getMenuRating()+review.getPlaceRating()+review.getServiceRating())/3;
        }
        System.out.println("Review Promedio: " + suma +"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Map<User, ReviewRestaurant> getReviewMap() {
        return reviewMap;
    }

    public void setReviewMap(Map<User, ReviewRestaurant> reviewMap) {
        this.reviewMap = reviewMap;
    }

    @Override
    public String toString() {
        return "____________________________" +
                "\n Nombre= " + name +
                "\n Direccion = " + address +
                "\n Menu = " + menu.getName() +
                "\n Cantidad de Reviews : " + reviewMap.size() +
                '}';
    }
}
