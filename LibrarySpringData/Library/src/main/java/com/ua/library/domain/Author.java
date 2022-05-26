package com.ua.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id",nullable = false)
    private long id;

    @Column(name = "surname",nullable = false,unique = true)
    private String surname;

    public Author(String surname) {
        this.surname = surname;
    }
}
