package com.ua.library;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.ua.library.domain.Genre;
import com.ua.library.service.BookService;
import com.ua.library.service.GenreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongock
public class LibraryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);

    }
}