package com.ua.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import com.mongodb.client.MongoDatabase;
import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.repository.AuthorRepository;
import com.ua.library.repository.BookRepository;
import com.ua.library.repository.GenreRepository;

@ChangeLog(order = "001")
public class DBChangeLog {
    private Author Oracle;
    private Genre Technic;

    @ChangeSet(order = "000", id = "dropDB", author = "admin", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }
    @ChangeSet(order = "001", id = "initGenre", author = "admin", runAlways = true)
    public void initGenre(GenreRepository genreRepository){
        Technic = genreRepository.save(new Genre("Technic"));
    }

    @ChangeSet(order = "002", id = "initAuthor", author = "admin", runAlways = true)
    public void initAuthor(AuthorRepository authorRepository){
        Oracle = authorRepository.save(new Author("Oracle"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "admin", runAlways = true)
    public void initBooks(BookRepository bookRepository){
        bookRepository.save(new Book("Java",Technic,Oracle));
        bookRepository.save(new Book("Spring",Technic,Oracle));
    }
}
