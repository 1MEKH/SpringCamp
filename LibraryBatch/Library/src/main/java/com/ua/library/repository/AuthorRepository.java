package com.ua.library.repository;

import com.ua.library.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findBySurname(String surname);
    void deleteBySurname(String surname);
}