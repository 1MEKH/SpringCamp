package com.ua.library.repository;

import com.ua.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getBySurname(String surname);
    void deleteBySurname(String surname);
    boolean existsBySurname(String Surname);
}