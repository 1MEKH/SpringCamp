package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Author;

public interface AuthorService {
    Author findBySurname(String surname);
}
