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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
    public static final String MESSAGE_NOT_FOUND_TICKET = "Билет по указанному id-%s не найден.";
    public static final String MESSAGE_TICKET_SOLD_OUT = "Билет id: %s уже продан.";
    private final JdbcTemplate jdbcTemplate;
    private final PlaceRepository placeRepository;
    private final SessionRepository sessionRepository;

    public List<Ticket> getUnsoldTickets() {
        String sql = """
                SELECT *
                FROM ticket
                WHERE is_bought = false
                """;

        return jdbcTemplate.query(sql, this::mapToTicket);

    }

    public List<Ticket> getSoldTickets() {
        String sql = """
                SELECT *
                FROM ticket
                WHERE is_bought = true
                """;
        return jdbcTemplate.query(sql, this::mapToTicket);
    }

    public void changeStatus(Integer id) {
        Ticket ticket = findById(id)
                .orElseThrow(() -> new NoSuchElementException(MESSAGE_NOT_FOUND_TICKET.formatted(id)));

//        if (ticket == null) {
//            throw new RuntimeException(MESSAGE_NOT_FOUND_TICKET.formatted(id));
//        }
        if (ticket.getIsBought()) {
            throw new RuntimeException(MESSAGE_TICKET_SOLD_OUT.formatted(id));
        }

        String sql = """
                UPDATE ticket SET is_bought = true
                WHERE id = ?;
                """;

        jdbcTemplate.update(sql, ps -> {
            ps.setInt(1, id);
        });

    }

    public Ticket save(Ticket ticket) {
        Integer placeId = ticket.getPlace().getId();
        Integer sessionId = ticket.getSession().getId();
        Boolean isBought = ticket.getIsBought();
        String sql = """
                INSERT INTO ticket (place_id, session_id, is_bought)
                VALUES (?, ?, ?) RETURNING id;
                """;

        Integer tickedId = jdbcTemplate.queryForObject(sql, Integer.class, placeId, sessionId, isBought);
        ticket.setId(tickedId);

        return ticket;

    }

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
