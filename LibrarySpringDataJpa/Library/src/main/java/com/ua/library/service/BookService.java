package com.ua.library.service;

import com.ua.library.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    Book findById(long id);
    List<Book> findAll();
    List<Book> findAllByAuthorSurname(String surname);
    List<Book> findAllByAuthorId(long id);
    List<Book> findAllByGenreType(String type);
    List<Book> findAllByGenreId(long id);
    long count();

}
