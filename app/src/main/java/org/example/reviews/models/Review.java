package org.example.reviews.models;

import java.time.LocalDate;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public abstract class Review {
    private Integer id;
    private String author;
    private String comment;
    private Float rating;
    private LocalDate date;

    public Review() {};

    public Review(Integer id, String author, String comment, Float rating, LocalDate date) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    };

    public abstract void reviewType();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}
