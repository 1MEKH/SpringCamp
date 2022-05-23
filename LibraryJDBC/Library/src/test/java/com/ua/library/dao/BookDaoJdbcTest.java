package com.ua.library.dao;

import com.ua.library.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({BookDaoJdbc.class,GenreDaoJdbc.class,AuthorDaoJdbc.class})
class BookDaoJdbcTest {

    private static final int EXPECTED_AUTHOR_COUNT=2;
    private static final String TEST_DATA_STRING="test";
    private static final long TEST_DATA_ID=20;
    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("count of books in table")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void count() {
        int count = bookDaoJdbc.count();
        assertEquals(count,EXPECTED_AUTHOR_COUNT);
    }

    @DisplayName("add book to table")
    @Test
    void insert() {

        Book expectedBook = new Book(TEST_DATA_ID,TEST_DATA_STRING,
                authorDaoJdbc.getById(1),genreDaoJdbc.getById(1));

        bookDaoJdbc.insert(expectedBook);
        Book actualBook = bookDaoJdbc.getById(TEST_DATA_ID);
        assertEquals(expectedBook,actualBook);
    }

    @DisplayName("update book")
    @Test
    void update() {
        Book expectedBook = new Book(1,TEST_DATA_STRING,
                authorDaoJdbc.getById(1),genreDaoJdbc.getById(1));
        bookDaoJdbc.update(expectedBook);

        Book actualBook = bookDaoJdbc.getById(1);
        assertEquals(expectedBook,actualBook);
    }

    @DisplayName("delete book dy id")
    @Test
    void deleteById() {
        bookDaoJdbc.deleteById(2);
        assertNull(bookDaoJdbc.getById(2));
    }

    @DisplayName("get book dy id")
    @Test
    void getById() {
        Book actualBook = bookDaoJdbc.getById(1);
        assertEquals(actualBook.getName(),"Java");
    }
}