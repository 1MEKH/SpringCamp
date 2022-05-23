package com.ua.library.dao;

import com.ua.library.domain.Author;
import com.ua.library.domain.Book;
import com.ua.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao{
    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static class BookMapper implements RowMapper<Book>{
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("book_id");
            String name = rs.getString("book_name");
            long authorId = rs.getLong("author_id");
            String surname = rs.getString("surname");
            long genresId = rs.getLong("genre_id");
            String type = rs.getString("type");

            return new Book(id,name,new Author(authorId,surname),new Genre(genresId,type));
        }
    }

    @Override
    public int count() {
        Integer count = jdbcOperations.getJdbcOperations().queryForObject(
                "SELECT COUNT(*) FROM books", Integer.class
        );
        if(count==null){
            return 0;
        }
        return count;
    }

    @Override
    public void insert(Book book) {
        jdbcOperations.update(
                "INSERT INTO books VALUES (:id,:name,:authorId,:genreId)",
                Map.of("id", book.getId(), "name",book.getName(),
                        "authorId",book.getAuthor().getId(),
                        "genreId",book.getGenre().getId())
        );
    }

    @Override
    public void update(Book book) {
        jdbcOperations.update(
                "UPDATE books SET book_name=:name, author_id=:authorId, genre_id=:genreId WHERE book_id=:id",
                Map.of("id", book.getId(), "name",book.getName(),
                        "authorId",book.getAuthor().getId(),
                        "genreId",book.getGenre().getId())
        );
    }

    @Override
    public void deleteById(long id) {
        jdbcOperations.update(
                "DELETE FROM books WHERE book_id=:id",
                Map.of("id",id));
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query(
                "SELECT b.book_id, b.book_name, a.author_id, a.surname, g.genre_id, g.type " +
                        "FROM books b " +
                        "JOIN authors a USING(author_id)" +
                        "JOIN genres g USING(genre_id)",
                new BookMapper()
        );
    }

    @Override
    public Book getById(long id) {
        return jdbcOperations.query(
                "SELECT b.book_id, b.book_name, a.author_id, a.surname, g.genre_id, g.type " +
                        "FROM books b " +
                        "JOIN authors a USING(author_id)" +
                        "JOIN genres g USING(genre_id)" +
                        "WHERE b.book_id=:id",
                Map.of("id",id),
                new BookMapper()
        ).stream().findFirst().orElse(null);
    }

}
