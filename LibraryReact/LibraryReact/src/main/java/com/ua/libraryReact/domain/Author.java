package com.ua.libraryReact.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;
    @Field(name = "surname")
    private String surname;

    public Author(String surname) {
        this.surname = surname;
    }
}