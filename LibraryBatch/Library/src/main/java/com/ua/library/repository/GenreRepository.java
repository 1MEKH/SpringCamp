package com.ua.library.repository;

import com.ua.library.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByType(String type);
}