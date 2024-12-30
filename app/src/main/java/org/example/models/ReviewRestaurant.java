package org.example.models;

public class ReviewRestaurant implements IReview {

    private Float serviceRating;
    private Float placeRating;
    private Float menuRating;
    private String comment;

    public ReviewRestaurant(String comment, Float serviceRating, Float placeRating, Float menuRating) {
        this.comment=comment;
        this.serviceRating = serviceRating;
        this.placeRating = placeRating;
        this.menuRating = menuRating;
    }

    @Override
    public void showDetails() {
        System.out.println("Comentario: "+comment);
        System.out.println("califacion de servicio :"+serviceRating);
        System.out.println("califacion de lugar: "+placeRating);
        System.out.println("califacion de menu: "+menuRating);
    }

    public Float getServiceRating() {
        return serviceRating;
    }



    public Float getPlaceRating() {
        return placeRating;
    }



    public Float getMenuRating() {
        return menuRating;
    }



    public String getComment() {
        return comment;
    }
}
