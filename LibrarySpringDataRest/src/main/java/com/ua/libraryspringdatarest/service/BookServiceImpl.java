package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Author;
import com.ua.libraryspringdatarest.domain.Book;
import com.ua.libraryspringdatarest.domain.Genre;
import com.ua.libraryspringdatarest.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void addBook(Book book) {
        addOrUpdate(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(long id, Book book) {
        if(bookRepository.findById(id).orElse(null) != null){
            addOrUpdate(book);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    private void addOrUpdate(Book book){

        Author author = authorService.findBySurname(book.getAuthor().getSurname());
        if (author == null) author = new Author(book.getAuthor().getSurname());

        Genre genre = genreService.findByType(book.getGenre().getType());
        if (genre == null) genre = new Genre(book.getGenre().getType());

        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }
}
