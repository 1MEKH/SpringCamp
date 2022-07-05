package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Genre;
import com.ua.libraryspringdatarest.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findByType(String type) {
        return genreRepository.findByType(type);
    }
}
