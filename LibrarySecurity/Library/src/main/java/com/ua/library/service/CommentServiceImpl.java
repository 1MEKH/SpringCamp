package com.ua.library.service;

import com.ua.library.domain.Book;
import com.ua.library.domain.Comment;
import com.ua.library.repository.CommentRepository;
import com.ua.library.service.IO.IOService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final IOService ioService;

    public CommentServiceImpl(CommentRepository commentRepository, IOService ioService) {
        this.commentRepository = commentRepository;
        this.ioService = ioService;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getByText(String text) {
        return commentRepository.getByText(text);
    }

    @Override
    public List<Comment> getAllByBook(Book book) {
        return commentRepository.getAllByBook(book);
    }

    @Transactional
    @Override
    public void deleteByText(String text) {
        commentRepository.deleteByText(text);
    }

    @Transactional
    @Override
    public void addNew(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteAllByBook(Book book) {
        commentRepository.deleteAllByBook(book);
    }

    @Override
    public void deleteById(long id) {
        if(getById(id) != null)
        {
            commentRepository.deleteById(id);
        }
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.getById(id);
    }

}
