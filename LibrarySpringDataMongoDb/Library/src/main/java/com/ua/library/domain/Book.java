package com.ua.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;

    @Field(name = "tittle")
    private String tittle;

    @DBRef
    private Genre genre;

    @DBRef
    private Author author;

    public Book(String tittle, Genre genre, Author author) {
        this.tittle = tittle;
        this.genre = genre;
        this.author = author;
    }
}
