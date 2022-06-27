package com.ua.library.repository;

import com.ua.library.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByAuthorSurname(String surname);
    List<Book> findAllByAuthorId(String id);

    List<Book> findAllByGenreType(String type);
    List<Book> findAllByGenreId(String id);

    void deleteByTittle(String tittle);
    Book findByTittle(String tittle);
}