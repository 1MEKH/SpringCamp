package com.ua.library.service;


import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.repository.BookRepository;
import com.ua.library.service.IO.IOService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final CommentService commentService;
    private final IOService ioService;

    public BookServiceImpl(BookRepository bookRepository, GenreService genreService,
                           CommentService commentService, IOService ioService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.commentService = commentService;
        this.ioService = ioService;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getByTittle(String tittle) {
        return bookRepository.getByTittle(tittle);
    }

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Transactional
    @Override
    public void deleteBook(Book book) {
        String tittle = book.getTittle();
        if(alreadyInDB(tittle)) {
            bookRepository.deleteByTittle(tittle);
            commentService.deleteAllByBook(book);
        }
        else{
            ioService.out("[-] Book with this tittle not found");
        }
    }

    @Transactional
    @Override
    public void addNew(Book book) {
        if(!alreadyInDB(book.getTittle())) {
            String genreType = book.getGenre().getType();
            if(genreService.alreadyInDB(genreType)){
                Genre genre = genreService.getByType(genreType);
                System.out.println(genre);
                book.setGenre(genre);
            }
            bookRepository.save(book);
        }
        else {
            ioService.out("[-] Book is already in db");
            bookRepository.getByTittle(book.getTittle());
        }
    }

    @Transactional
    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }

    private boolean alreadyInDB(String tittle){
        return bookRepository.existsByTittle(tittle);
    }

}
