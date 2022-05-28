package com.ua.library.repositiry;

import com.ua.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorSurname(String surname);
    List<Book> findAllByAuthorId(long id);

    List<Book> findAllByGenreType(String type);
    List<Book> findAllByGenreId(long id);


}