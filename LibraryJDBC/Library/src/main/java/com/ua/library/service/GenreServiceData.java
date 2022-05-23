package com.ua.library.service;

import com.ua.library.dao.GenreDaoJdbc;
import com.ua.library.domain.Genre;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceData implements DataService<Genre>{

    private final IOServiceImpl ioService;
    private final GenreDaoJdbc genreDaoJdbc;

    public GenreServiceData(IOServiceImpl ioService, GenreDaoJdbc genreDaoJdbc) {
        this.ioService = ioService;
        this.genreDaoJdbc = genreDaoJdbc;
    }


    @Override
    public int count() {
        return genreDaoJdbc.count();
    }

    @Override
    public void insert(Genre genre) {

        if(isAlreadyThere(genre.getId())){
            ioService.out("This id is used");
        }else{
            genreDaoJdbc.insert(genre);
        }
    }

    @Override
    public void update(Genre genre) {

        if(isAlreadyThere(genre.getId())){
            genreDaoJdbc.update(genre);
        }else{
            ioService.out("No genre with id " + genre.getId());
        }
    }

    @Override
    public void deleteById(long id) {

        if(isAlreadyThere(id)){
            genreDaoJdbc.deleteById(id);
        }else{
            ioService.out("No genre with id " + id);
        }
    }

    @Override
    public boolean isAlreadyThere(long id){
        return genreDaoJdbc.getById(id) != null;
    }

    @Override
    public Genre getById(long id) {
        return genreDaoJdbc.getById(id);
    }

    @Override
    public void showAll() {
        for(Genre temp: genreDaoJdbc.getAll()){
            ioService.out(temp.toString());
        }
    }

}
