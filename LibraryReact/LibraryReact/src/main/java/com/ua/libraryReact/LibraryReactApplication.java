package com.ua.libraryReact;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableMongock
public class LibraryReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryReactApplication.class, args);
    }

}
