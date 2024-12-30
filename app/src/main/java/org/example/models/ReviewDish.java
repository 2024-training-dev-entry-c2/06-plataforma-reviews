package org.example.models;

public class ReviewDish implements IReview {
    private Float tasteRating;
    private Float presentationRating;
    private String comment;


    public ReviewDish(String comment, Float tasteRating, Float presentationRating) {
        this.comment=comment;
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
    }
    @Override
    public void showDetails() {
        System.out.println("Comentario: "+comment);
        System.out.println("califacion de presentacion de plato :"+presentationRating);
        System.out.println("califacion de sabor: "+tasteRating);
    }

    public Float getTasteRating() {
        return tasteRating;
    }


    public Float getPresentationRating() {
        return presentationRating;
    }

}
