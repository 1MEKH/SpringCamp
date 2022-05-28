package com.ua.library.service;

import com.ua.library.domain.Author;
import com.ua.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findBySurname(String surname) {
        return authorRepository.findBySurname(surname);
    }

    @Override
    public Author deleteBySurname(String surname) {
        return authorRepository.deleteBySurname(surname);
    }
}
