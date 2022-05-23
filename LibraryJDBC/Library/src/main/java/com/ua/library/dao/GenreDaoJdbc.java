package com.ua.library.dao;

import com.ua.library.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations jdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("genre_id");
            String type = rs.getString("type");
            return new Genre(id,type);
        }
    }

    @Override
    public int count() {
        Integer count = jdbcOperations.getJdbcOperations().queryForObject(
                "SELECT COUNT(*) FROM genres", Integer.class
        );
        if(count==null){
            return 0;
        }
        return count;
    }

    @Override
    public void insert(Genre genre) {
        jdbcOperations.update(
                "INSERT INTO genres VALUES (:id,:type)",
                Map.of("id", genre.getId(), "type",genre.getType())
        );
    }

    @Override
    public void update(Genre genre) {
        jdbcOperations.update(
                "UPDATE genres SET type=:type WHERE genre_id=:id",
                Map.of("id", genre.getId(), "type",genre.getType())
        );
    }

    @Override
    public void deleteById(long id) {
        jdbcOperations.update(
                "DELETE FROM genres WHERE genre_id=:id",
                Map.of("id",id));
    }

    @Override
    public List<Genre> getAll() {
        return jdbcOperations.query(
                "SELECT * FROM genres", new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public Genre getById(long id) {
        return jdbcOperations.query(
                "SELECT * FROM genres WHERE genre_id=:id",
                Map.of("id",id),
                new GenreDaoJdbc.GenreMapper()
        ).stream().findFirst().orElse(null);
    }

}
