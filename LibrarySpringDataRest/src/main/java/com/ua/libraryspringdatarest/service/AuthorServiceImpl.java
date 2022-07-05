package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Author;
import com.ua.libraryspringdatarest.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findBySurname(String surname) {
        return authorRepository.findBySurname(surname);
    }
}
