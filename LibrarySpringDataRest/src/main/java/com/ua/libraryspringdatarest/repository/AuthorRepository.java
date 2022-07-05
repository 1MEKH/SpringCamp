package com.ua.libraryspringdatarest.repository;

import com.ua.libraryspringdatarest.domain.Author;
import com.ua.libraryspringdatarest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @RestResource(path = "surname", rel = "surname")
    Author findBySurname(String surname);
}