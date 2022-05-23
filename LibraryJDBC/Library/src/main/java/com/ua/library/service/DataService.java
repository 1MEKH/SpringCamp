package com.ua.library.service;

import com.ua.library.domain.Author;

public interface DataService<T> {
    int count();
    void insert(T data);
    void update(T data);
    void deleteById(long id);
    void showAll();
    boolean isAlreadyThere(long id);

    T getById(long id);
}
