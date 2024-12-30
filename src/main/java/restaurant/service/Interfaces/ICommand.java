package restaurant.service.Interfaces;

public interface ICommand<T> {
    T execute();
}
