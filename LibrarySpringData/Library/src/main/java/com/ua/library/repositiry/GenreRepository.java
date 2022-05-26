package com.ua.library.repositiry;

import com.ua.library.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByType(String type);
}