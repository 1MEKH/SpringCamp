package com.ua.libraryspringdatarest.repository;

import com.ua.libraryspringdatarest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

    @RestResource(path = "tittle", rel = "tittle")
    List<Book> findByTittle(String tittle);
}