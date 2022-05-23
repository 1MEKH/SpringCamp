package com.ua.library.dao;

import com.ua.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRE_COUNT=2;
    private static final String TEST_DATA_STRING="test";
    private static final long TEST_DATA_ID=15;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("count of genres in table")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void count() {
        int count = genreDaoJdbc.count();
        assertEquals(count,EXPECTED_GENRE_COUNT);
    }

    @DisplayName("add genre to table")
    @Test
    void insert() {
        Genre expectedGenre = new Genre(TEST_DATA_ID,TEST_DATA_STRING);
        genreDaoJdbc.insert(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getById(TEST_DATA_ID);
        assertEquals(actualGenre,expectedGenre);
    }

    @DisplayName("get genre dy id")
    @Test
    void getById() {
        Genre actualGenre = genreDaoJdbc.getById(1);
        assertEquals(actualGenre.getType(),"Technic");
    }

    @DisplayName("update genre")
    @Test
    void update() {
        Genre expectedGenre = new Genre(1,TEST_DATA_STRING);
        genreDaoJdbc.update(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getById(1);
        assertEquals(actualGenre,expectedGenre);
    }

    @DisplayName("delete genre dy id")
    @Test
    void deleteById() {
        Genre expectedGenre = new Genre(TEST_DATA_ID,TEST_DATA_STRING);
        genreDaoJdbc.insert(expectedGenre);
        genreDaoJdbc.deleteById(TEST_DATA_ID);
        assertNull(genreDaoJdbc.getById(TEST_DATA_ID));
    }

}