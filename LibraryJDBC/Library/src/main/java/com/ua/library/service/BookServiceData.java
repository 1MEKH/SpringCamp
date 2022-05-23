package com.ua.library.service;

import com.ua.library.dao.BookDaoJdbc;
import com.ua.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceData implements DataService<Book>{

    private final IOServiceImpl ioService;
    private final BookDaoJdbc bookDaoJdbc;
    private final AuthorServiceData authorServiceData;
    private final GenreServiceData genreServiceData;

    @Autowired
    public BookServiceData(IOServiceImpl ioService, BookDaoJdbc bookDaoJdbc,
                           AuthorServiceData authorServiceData, GenreServiceData genreServiceData) {
        this.ioService = ioService;
        this.bookDaoJdbc = bookDaoJdbc;
        this.authorServiceData = authorServiceData;
        this.genreServiceData = genreServiceData;
    }

    @Override
    public int count() {
        return bookDaoJdbc.count();
    }

    @Override
    public void insert(Book book) {

        if(isAlreadyThere(book.getId())){
            ioService.out("This id is used");
        }else{
            insertNewGenre(book);
            insertNewAuthor(book);
            bookDaoJdbc.insert(book);
        }
    }

    @Override
    public void update(Book book) {

        if(isAlreadyThere(book.getId())){
            bookDaoJdbc.update(book);
        }else{
            ioService.out("No book with id " + book.getId());
        }
    }

    private void insertNewAuthor(Book book){
        if(!authorServiceData.isAlreadyThere(book.getAuthor().getId())){
            authorServiceData.insert(book.getAuthor());
        }
    }

    private void insertNewGenre(Book book){
        if(!genreServiceData.isAlreadyThere(book.getGenre().getId())){
            genreServiceData.insert(book.getGenre());
        }
    }

    @Override
    public void deleteById(long id) {
        if(isAlreadyThere(id)){
            bookDaoJdbc.deleteById(id);
        }else{
            ioService.out("No book with id " + id);
        }
    }

    @Override
    public boolean isAlreadyThere(long id){
        return bookDaoJdbc.getById(id) != null;
    }

    @Override
    public Book getById(long id) {
        return bookDaoJdbc.getById(id);
    }

    @Override
    public void showAll() {
        for(Book temp: bookDaoJdbc.getAll()){
            ioService.out(temp.toString());
        }
    }


}
