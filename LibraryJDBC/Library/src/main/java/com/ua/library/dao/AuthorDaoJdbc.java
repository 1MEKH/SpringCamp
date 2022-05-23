package com.ua.library.dao;

import com.ua.library.domain.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("author_id");
            String surname = rs.getString("surname");
            return new Author(id,surname);
        }
    }

    @Override
    public int count() {
        Integer count = jdbcOperations.getJdbcOperations().queryForObject(
                "SELECT COUNT(*) FROM authors", Integer.class
        );
        if(count==null){
            return 0;
        }
        return count;
    }

    @Override
    public void insert(Author author) {
        jdbcOperations.update(
                "INSERT INTO authors VALUES (:id,:surname)",
                Map.of("id", author.getId(), "surname",author.getSurname())
        );
    }

    @Override
    public void update(Author author) {
        jdbcOperations.update(
                "UPDATE authors SET surname=:surname WHERE author_id=:id",
                Map.of("id", author.getId(), "surname",author.getSurname())
        );
    }

    @Override
    public void deleteById(long id) {
        jdbcOperations.update(
                "DELETE FROM authors WHERE author_id=:id",
                Map.of("id",id));
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query(
                "SELECT * FROM authors", new AuthorDaoJdbc.AuthorMapper()
        );
    }

    @Override
    public Author getById(long id) {
        return jdbcOperations.query(
                "SELECT * FROM authors WHERE author_id=:id",
                Map.of("id",id),
                new AuthorDaoJdbc.AuthorMapper()
        ).stream().findFirst().orElse(null);
    }

}
