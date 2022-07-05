package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void addBook(Book book);

    Book findById(long id);

    void update(long id, Book book);

    void deleteById(Long id);
}
