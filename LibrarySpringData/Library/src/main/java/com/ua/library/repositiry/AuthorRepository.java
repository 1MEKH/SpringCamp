package com.ua.library.repositiry;

import com.ua.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findBySurname(String surname);

}