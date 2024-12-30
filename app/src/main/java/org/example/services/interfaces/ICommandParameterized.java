package org.example.services.interfaces;

public interface ICommandParameterized <T, R>{
  T execute(R parameter);
}