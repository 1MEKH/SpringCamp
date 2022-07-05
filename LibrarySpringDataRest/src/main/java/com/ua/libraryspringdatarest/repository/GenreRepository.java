package com.ua.libraryspringdatarest.repository;

import com.ua.libraryspringdatarest.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @RestResource(path = "type", rel = "type")
    Genre findByType(String type);
}