package com.ua.library.repositiry;

import com.ua.library.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findBySurname(String surname);
    Author deleteBySurname(String surname);
}