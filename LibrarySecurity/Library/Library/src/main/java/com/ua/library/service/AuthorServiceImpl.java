package com.ua.library.service;

import com.ua.library.domain.Author;
import com.ua.library.repository.AuthorRepository;
import com.ua.library.service.IO.IOService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final IOService ioService;

    public AuthorServiceImpl(AuthorRepository authorRepository, IOService ioService) {
        this.authorRepository = authorRepository;
        this.ioService = ioService;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getBySurname(String surname) {
        return authorRepository.getBySurname(surname);
    }

    @Transactional
    @Override
    public void deleteBySurname(String surname) {
        if(alreadyInDB(surname)) {
            authorRepository.deleteBySurname(surname);
        }
        else{
            ioService.out("[-] Author with this surname not found");
        }
    }

    @Transactional
    @Override
    public void addNew(Author author) {
        if(!alreadyInDB(author.getSurname())) {
            authorRepository.save(author);
        }
        else {
            ioService.out("[-] Author is already in db");
            authorRepository.getBySurname(author.getSurname());
        }
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }
    @Override
    public boolean alreadyInDB(String surname){
        return authorRepository.existsBySurname(surname);
    }
}
