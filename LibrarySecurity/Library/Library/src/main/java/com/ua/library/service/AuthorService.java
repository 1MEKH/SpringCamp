package com.ua.library.service;

import com.ua.library.domain.Author;


import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author getBySurname(String surname);
    void deleteBySurname(String surname);
    void addNew(Author author);
    void update(Author author);
    boolean alreadyInDB(String surname);
}
