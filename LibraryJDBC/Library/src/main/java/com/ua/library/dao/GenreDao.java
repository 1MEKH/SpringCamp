package com.ua.library.dao;

import com.ua.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    void insert(Genre genre);
    void update(Genre genre);
    void deleteById(long id);
    List<Genre> getAll();
    Genre getById(long id);
}
