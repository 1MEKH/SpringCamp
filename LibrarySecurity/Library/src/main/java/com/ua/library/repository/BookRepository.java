package com.ua.library.repository;

import com.ua.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getByTittle(String tittle);
    void deleteByTittle(String tittle);
    boolean existsByTittle(String tittle);
}