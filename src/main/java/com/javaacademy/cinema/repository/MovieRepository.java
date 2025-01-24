package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Movie> selectAll() {
        String sql = "SELECT * FROM movie;";
        return jdbcTemplate.query(sql, this::mapToMovie);
    }

    public Movie save(Movie movie) {
        String sql = """
                INSERT INTO movie(name, description)
                 VALUES (?, ?)
                 RETURNING id;
                """;
        String name = movie.getName();
        String description = movie.getDescription();

        Integer createdId = jdbcTemplate.queryForObject(sql, Integer.class, name, description);
        movie.setId(createdId);

        return movie;
    }

    public Optional<Movie> findById(Integer id) {
        String sql = """
                SELECT * FROM movie
                WHERE id = ?;
                """;
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, this::mapToMovie, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    private Movie mapToMovie(ResultSet rs, int rowNum) {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setName(rs.getString("name"));
        movie.setDescription(rs.getString("description"));
        return movie;
    }
}
