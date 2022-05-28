package com.ua.library.service;

import com.ua.library.domain.Book;
import com.ua.library.repositiry.BookRepository;
import com.ua.library.service.IO.IOService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final IOService ioService;

    public BookServiceImpl(BookRepository bookRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.ioService = ioService;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByAuthorSurname(String surname) {
        return bookRepository.findAllByAuthorSurname(surname);
    }

    @Override
    public List<Book> findAllByAuthorId(long id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Book> findAllByGenreType(String type) {
        return bookRepository.findAllByGenreType(type);
    }

    @Override
    public List<Book> findAllByGenreId(long id) {
        return bookRepository.findAllByGenreId(id);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

}
