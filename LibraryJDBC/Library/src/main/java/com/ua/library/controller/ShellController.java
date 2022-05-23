package com.ua.library.controller;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellController {
    private final BookServiceData bookServiceData;
    private final AuthorServiceData authorServiceData;
    private final GenreServiceData genreServiceData;
    private final IOService ioService;

    @Autowired
    public ShellController(BookServiceData bookServiceData, AuthorServiceData authorServiceData,
                           GenreServiceData genreServiceData, IOService ioService) {
        this.bookServiceData = bookServiceData;
        this.authorServiceData = authorServiceData;
        this.genreServiceData = genreServiceData;
        this.ioService = ioService;
    }

    @ShellMethod(value = "SHOW ALL BOOKS",key = {"all books","allB"})
    public void allBooks(){
        bookServiceData.showAll();
    }

    @ShellMethod(value = "ADD NEW BOOK", key = {"add book","addB"})
    public void addBook(){
        Book book = createBook();
        bookServiceData.insert(book);
    }

    @ShellMethod(value = "DELETE BY ID", key = {"delete book","delB"})
    public void deleteBook(){
        ioService.out("Write book id ::");
        long bookId = ioService.readLong();
        bookServiceData.deleteById(bookId);
    }

    @ShellMethod(value = "SHOW BY ID", key = {"show book","shB"})
    public void showBook(){
        ioService.out("Write book id ::");
        long bookId = ioService.readLong();
        ioService.out(""+bookServiceData.getById(bookId));
    }

    @ShellMethod(value = "COUNT OF BOOKS", key = {"count of books","cB"})
    public void countOfBooks(){
        ioService.out("Count of books = "+ bookServiceData.count());
    }

    private Book createBook(){
        ioService.out("Write book id ::");
        long bookId = ioService.readLong();
        ioService.out("Write book name ::");
        String bookName = ioService.readString();

        ioService.out("Write author id ::");
        long authorId = ioService.readLong();

        String authorSurname;
        if(!authorServiceData.isAlreadyThere(authorId)) {
            ioService.out("Write author surname ::");
            authorSurname = ioService.readString();
        }else{
            authorSurname = authorServiceData.getById(authorId).getSurname();
        }

        ioService.out("Write genre id ::");
        long genreId = ioService.readLong();
        String genreType;
        if(!genreServiceData.isAlreadyThere(genreId)){
            ioService.out("Write genre ::");
            genreType = ioService.readString();
        }else{
            genreType = genreServiceData.getById(genreId).getType();
        }

        return new Book(bookId,bookName,new Author(authorId, authorSurname),new Genre(genreId,genreType));
    }
}
