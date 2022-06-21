package com.ua.library.service;

import com.ua.library.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getByTittle(String tittle);
    Book getById(long id);
    void deleteBook(Book book);
    void addNew(Book book);
    void update(Book book);
}
