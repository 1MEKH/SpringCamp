package com.ua.library.service;

import com.ua.library.domain.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);
    Author findById(long id);
    List<Author> findAll();
    Author findBySurname(String surname);
}
