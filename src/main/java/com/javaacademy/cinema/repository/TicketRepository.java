package com.javaacademy.cinema.repository;

import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PlaceRepository placeRepository;
    private final SessionRepository sessionRepository;

    public Optional<Ticket> findById(Integer id) {
        String sql = """
                SELECT *
                FROM ticket
                WHERE id = ?
                """;

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, this::mapToTicket, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    private Ticket mapToTicket(ResultSet rs, int rowNum) {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setIsBought(rs.getBoolean("is_bought"));

        if (rs.getString("place_id") != null) {
            Integer placeId = rs.getInt("place_id");
            Place place = placeRepository.findById(placeId).orElse(null);
            ticket.setPlace(place);
        }

        if (rs.getString("session_id") != null) {
            Integer sessionId = rs.getInt("session_id");
            Session session = sessionRepository.findById(sessionId).orElse(null);
            ticket.setSession(session);
        }

        return ticket;
    }
}
