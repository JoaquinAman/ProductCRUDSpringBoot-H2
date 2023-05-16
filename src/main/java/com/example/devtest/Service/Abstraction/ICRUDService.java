package com.example.devtest.Service.Abstraction;

import java.util.List;

public interface ICRUDService<T, U> {
    T create(U entity);
    void deleteById(Long id);
    T getById(Long id);
    List<T> findAll();
    T update(Long id, U entity);

}
