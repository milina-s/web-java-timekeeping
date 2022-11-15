package com.example.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findById(Long id);

    void create(T t);

    void update(Long id, T newItem);

    void delete(Long id);
}
