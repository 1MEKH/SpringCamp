package com.ua.library.service;

import com.ua.library.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getByType(String type);
    void deleteByType(String type);
    void addNew(Genre genre);
    void update(Genre genre);
    boolean alreadyInDB(String type);
}
