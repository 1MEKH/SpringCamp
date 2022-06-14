package com.ua.libraryReact.repository;

import com.ua.libraryReact.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveMongoRepository<Book,String> {
    Mono<Book> findByTittle(String tittle);
    Mono<Void> deleteByTittle(String tittle);
}
