package com.ua.library.processor;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import com.ua.library.domain.dto.AuthorDTO;
import com.ua.library.domain.dto.BookDTO;
import com.ua.library.domain.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


@Service
public class ConvertorIdProcessor {

    public GenreDTO updateGenreId(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(conventStringToLong(genre.getId()));
        genreDTO.setType(genre.getType());
        return genreDTO;
    }

    public AuthorDTO updateAuthorId(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(conventStringToLong(author.getId()));
        authorDTO.setSurname(author.getSurname());
        return authorDTO;
    }

    public BookDTO updateBookId(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(conventStringToLong(book.getId()));
        bookDTO.setAuthorId(conventStringToLong(book.getAuthor().getId()));
        bookDTO.setGenreId(conventStringToLong(book.getGenre().getId()));
        bookDTO.setTittle(book.getTittle());
        return bookDTO;
    }

    private long conventStringToLong(String id){
//        id = id.replaceAll("[a-z]", "");
        String temp = id.substring(id.length()-2);
        return Long.parseLong(temp,36);
    }

}
