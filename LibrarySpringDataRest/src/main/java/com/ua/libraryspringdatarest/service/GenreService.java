package com.ua.libraryspringdatarest.service;

import com.ua.libraryspringdatarest.domain.Genre;

public interface GenreService {
    Genre findByType(String type);
}
