package main.java.org.example.services.interfaces;

public interface ICommand<T> {
    T execute();
}