package com.ua.library.controller;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.service.AuthorService;
import com.ua.library.service.BookService;
import com.ua.library.service.GenreService;
import com.ua.library.service.IO.IOService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final IOService ioService;

    public ShellController(BookService bookService, AuthorService authorService,
                           GenreService genreService, IOService ioService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "SHOW ALL BOOKS WHERE AUTHOR SURNAME ",key = {"books where author surname","allBwAs"})
    public void findAllByAuthorSurname(){
        ioService.out("Write author surname ::");
        String authorSurname = ioService.read();

        bookService.findAllByAuthorSurname(authorSurname)
                .forEach((book) -> ioService.out(book.toString()));
    }

    @ShellMethod(value = "SHOW ALL BOOKS WHERE GENRE TYPE ",key = {"books where genre type","allBwGt"})
    public void findAllByGenreType(){
        ioService.out("Write genre type ::");
        String genreType = ioService.read();

        bookService.findAllByGenreType(genreType)
                .forEach((book) -> ioService.out(book.toString()));
    }

    @ShellMethod(value = "SHOW ALL BOOKS",key = {"all books","allB"})
    public void allBooks(){
        bookService.findAll().forEach((book) -> ioService.out(book.toString()));
    }

    @ShellMethod(value = "COUNT OF BOOKS", key = {"count of books","cB"})
    public void countOfBooks(){
        ioService.out("Count of books = "+ bookService.count());
    }

    @ShellMethod(value = "ADD NEW BOOK", key = {"add book","addB"})
    public Book addBook(){
        ioService.out("Write book name ::");
        String bookName = ioService.read();

        Author author = addAuthor();
        Genre genre = addGenre();

        return bookService.save(new Book(bookName,genre,author));
    }

    @ShellMethod(value = "ADD NEW AUTHOR", key = {"add author","addA"})
    public Author addAuthor(){
        ioService.out("Write author surname ::");
        String authorSurname = ioService.read();
        Author author = authorService.findBySurname(authorSurname);
        if(author == null){
            author = authorService.save(new Author(authorSurname));
            ioService.out("[+] " + authorSurname + " created");
        }
        return author;
    }

    @ShellMethod(value = "ADD NEW GENRE", key = {"add genre","addG"})
    public Genre addGenre(){
        ioService.out("Write genre type ::");
        String genreType = ioService.read();
        Genre genre = genreService.findByType(genreType);
        if(genre == null){
            genre = genreService.save(new Genre(genreType));
            ioService.out("[+] " + genreType + " created");
        }
        return genre;
    }


}
