package com.ua.restclient.controller;

import com.ua.restclient.domain.Book;
import com.ua.restclient.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.findById(id);
    }

    @GetMapping("/book")
    public List<Book> getBooks(){
        return  bookService.findAll();
    }
}
