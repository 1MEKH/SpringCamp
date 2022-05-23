package com.ua.library.service;

import com.ua.library.dao.AuthorDaoJdbc;
import com.ua.library.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceData implements DataService<Author>{

    private final IOServiceImpl ioService;
    private final AuthorDaoJdbc authorDaoJdbc;

    @Autowired
    public AuthorServiceData(IOServiceImpl ioService, AuthorDaoJdbc authorDaoJdbc) {
        this.ioService = ioService;
        this.authorDaoJdbc = authorDaoJdbc;
    }

    @Override
    public int count() {
        return authorDaoJdbc.count();
    }

    @Override
    public void insert(Author author) {

        if(isAlreadyThere(author.getId())){
            ioService.out("This id is used");
        }else{
            authorDaoJdbc.insert(author);
        }
    }

    @Override
    public void update(Author author) {

        if(isAlreadyThere(author.getId())){
            authorDaoJdbc.update(author);
        }else{
            ioService.out("No author with id " + author.getId());
        }
    }

    @Override
    public void deleteById(long id) {

        if(isAlreadyThere(id)){
            authorDaoJdbc.deleteById(id);
        }else{
            ioService.out("No author with id " + id);
        }
    }

    @Override
    public boolean isAlreadyThere(long id){
        return authorDaoJdbc.getById(id) != null;
    }

    @Override
    public Author getById(long id) {
        return authorDaoJdbc.getById(id);
    }

    @Override
    public void showAll() {
        for(Author temp: authorDaoJdbc.getAll()){
            ioService.out(temp.toString());
        }
    }

}
