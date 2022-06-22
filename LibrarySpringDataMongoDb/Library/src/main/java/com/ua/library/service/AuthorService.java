package com.ua.library.service;

import com.ua.library.domain.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);
    Author findById(String id);
    List<Author> findAll();
    Author findBySurname(String surname);

    void deleteBySurname(String surname);
}
