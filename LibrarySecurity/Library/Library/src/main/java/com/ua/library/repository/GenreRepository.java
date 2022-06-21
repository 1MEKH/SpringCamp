package com.ua.library.repository;

import com.ua.library.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getByType(String type);
    void deleteByType(String type);
    boolean existsByType(String type);
}