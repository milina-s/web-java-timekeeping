package com.example.delegator;

public interface Delegator <T> {
    T getByClass(Class<?> findClass) throws ClassNotFoundException;
}
