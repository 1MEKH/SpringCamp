package com.ua.libraryReact.controller;


import com.ua.libraryReact.domain.Book;
import com.ua.libraryReact.repository.BookRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("book/{tittle}")
    public Mono<Book> byTittle(@PathVariable String tittle){
        return bookRepository.findByTittle(tittle);
    }

    @GetMapping("/book")
    public Flux<Book> all(){
        return bookRepository.findAll();
    }

    @GetMapping("/")
    public Mono<String> hello(){
        return Mono.just("hello").map(String::toUpperCase);
    }

    @GetMapping("/book/{tittle}/delete")
    public Mono<Void> delete(@PathVariable String tittle){
        return bookRepository.deleteByTittle(tittle);
    }

}
