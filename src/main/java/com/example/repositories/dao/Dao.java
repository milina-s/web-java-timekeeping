package com.example.repositories.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> findAll();

    T findById(Long id);

    void create(T t);

    void update(Long id, T newItem);

    void delete(Long id);

}
