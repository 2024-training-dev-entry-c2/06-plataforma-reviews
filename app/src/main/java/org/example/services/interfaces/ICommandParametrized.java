package org.example.services.interfaces;

public interface ICommandParametrized <T, R> {
    T execute(R parameter);
}
