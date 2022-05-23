package com.ua.library.dao;

import com.ua.library.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book book);
    void update(Book book);
    void deleteById(long id);
    List<Book> getAll();
    Book getById(long id);
}
