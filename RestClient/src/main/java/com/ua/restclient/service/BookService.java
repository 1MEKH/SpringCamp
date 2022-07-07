package com.ua.restclient.service;

import com.ua.restclient.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(long id);
}
