package com.ua.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",nullable = false)
    private long id;

    @Column(name = "tittle",nullable = false)
    private String tittle;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genre_id",foreignKey = @ForeignKey(name="FK_Genre"))
    private Genre genre;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id",foreignKey = @ForeignKey(name="FK_Author"))
    private Author author;

    public Book(String tittle, Genre genre, Author author) {
        this.tittle = tittle;
        this.genre = genre;
        this.author = author;
    }
}
