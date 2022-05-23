package com.ua.library.dao;

import com.ua.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author author);
    void update(Author author);
    void deleteById(long id);
    List<Author> getAll();
    Author getById(long id);
}
