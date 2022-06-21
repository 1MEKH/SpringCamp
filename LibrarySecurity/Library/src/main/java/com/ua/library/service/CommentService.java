package com.ua.library.service;

import com.ua.library.domain.Book;
import com.ua.library.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getByText(String text);
    Comment getById(long id);
    List<Comment> getAllByBook(Book book);
    void deleteByText(String text);
    void addNew(Comment comment);
    void deleteAllByBook(Book book);

    void deleteById(long id);
}
