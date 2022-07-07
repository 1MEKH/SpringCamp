package com.ua.restclient.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private String tittle;
    private Author author;
    private Genre genre;
}
