package com.ua.library.service;

import com.ua.library.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    Book findById(String id);
    List<Book> findAll();
    List<Book> findAllByAuthorSurname(String surname);
    List<Book> findAllByAuthorId(String id);
    List<Book> findAllByGenreType(String type);
    List<Book> findAllByGenreId(String id);
    long count();

}
