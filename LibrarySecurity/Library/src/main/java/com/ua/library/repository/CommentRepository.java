package com.ua.library.repository;

import com.ua.library.domain.Book;
import com.ua.library.domain.Comment;
import com.ua.library.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment getByText(String text);
    List<Comment> getAllByBook(Book book);
    void deleteByText(String text);
    boolean existsByText(String text);
    void deleteAllByBook(Book book);
}