package com.ua.library.dao;

import com.ua.library.domain.Author;
import com.ua.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHOR_COUNT=2;
    private static final String TEST_DATA_STRING="test";
    private static final long TEST_DATA_ID=10;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("count of authors in table")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void count() {
        int count = authorDaoJdbc.count();
        assertEquals(count,EXPECTED_AUTHOR_COUNT);
    }

    @DisplayName("add author to table")
    @Test
    void insert() {
        Author expectedAuthor = new Author(TEST_DATA_ID,TEST_DATA_STRING);
        authorDaoJdbc.insert(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getById(TEST_DATA_ID);
        assertEquals(actualAuthor,expectedAuthor);
    }

    @DisplayName("get author dy id")
    @Test
    void getById() {
        Author actualAuthor = authorDaoJdbc.getById(1);
        assertEquals(actualAuthor.getSurname(),"Oracle");
    }

    @DisplayName("update author")
    @Test
    void update() {
        Author expectedAuthor = new Author(1,TEST_DATA_STRING);
        authorDaoJdbc.update(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getById(1);
        assertEquals(actualAuthor,expectedAuthor);
    }

    @DisplayName("delete author dy id")
    @Test
    void deleteById() {
        Author expectedAuthor = new Author(TEST_DATA_ID,TEST_DATA_STRING);
        authorDaoJdbc.insert(expectedAuthor);
        authorDaoJdbc.deleteById(TEST_DATA_ID);
        assertNull(authorDaoJdbc.getById(TEST_DATA_ID));
    }

}