package com.ua.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError(AccessDeniedException e){
        return ResponseEntity.of(Optional.of("Access error"));
    }

}
