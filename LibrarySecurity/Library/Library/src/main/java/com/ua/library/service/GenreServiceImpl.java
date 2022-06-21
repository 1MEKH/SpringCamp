package com.ua.library.service;

import com.ua.library.domain.Genre;
import com.ua.library.repository.GenreRepository;
import com.ua.library.service.IO.IOService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final IOService ioService;

    public GenreServiceImpl(GenreRepository genreRepository, IOService ioService) {
        this.genreRepository = genreRepository;
        this.ioService = ioService;
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getByType(String type) {
        return genreRepository.getByType(type);
    }


    @Override
    public void deleteByType(String type) {
        if(alreadyInDB(type)) {
            genreRepository.deleteByType(type);
        }
        else{
            ioService.out("[-] Genre with this type not found");
        }
    }


    @Override
    public void addNew(Genre genre) {
        if(!alreadyInDB(genre.getType())) {
            genreRepository.save(genre);
        }
        else {
            ioService.out("[-] Genre is already in db");
            genreRepository.getByType(genre.getType());
        }
    }

    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public boolean alreadyInDB(String type){
        return genreRepository.existsByType(type);
    }
}
