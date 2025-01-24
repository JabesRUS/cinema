package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Session;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SessionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final MovieRepository movieRepository;

    public Session save(Session session) {
        String sql = """
                INSERT INTO session (movie_id, date_time, price) VALUES (?,?, ?) RETURNING id;
                """;
        Integer movieId = session.getMovie().getId();
        LocalDateTime dateTime = session.getDateTime();
        BigDecimal price = session.getPrice();

        Integer createdId = jdbcTemplate.queryForObject(sql, Integer.class, movieId, dateTime, price);
        session.setId(createdId);

        return session;
    }

    public List<Session> selectAll() {
        String sql = "SELECT * FROM session;";
        return jdbcTemplate.query(sql, this::mapToSession);
    }

    public Optional<Session> findById(Integer id) {
        String sql = """
                SELECT *
                FROM session
                WHERE id = ?
                """;

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, this::mapToSession, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @SneakyThrows
    private Session mapToSession(ResultSet rs, int rowNum) {
        Session session = new Session();
        session.setId(rs.getInt("id"));
        session.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        session.setPrice(rs.getBigDecimal("price"));

        if (rs.getString("movie_id") != null) {
            Integer movieId = rs.getInt("movie_id");
            Movie movie = movieRepository.findById(movieId).orElse(null);
            session.setMovie(movie);
        }

        return session;
    }
}
