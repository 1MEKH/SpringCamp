package com.ua.library.service;

import com.ua.library.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre save(Genre genre);
    Genre findById(String id);
    List<Genre> findAll();
    Genre findByType(String type);
}
