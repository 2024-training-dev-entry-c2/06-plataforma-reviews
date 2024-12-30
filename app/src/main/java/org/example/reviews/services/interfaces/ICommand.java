package org.example.reviews.services.interfaces;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public interface ICommand<T>{
    T execute();
}
