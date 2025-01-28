package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.mapper.SessionMapper;
import com.javaacademy.cinema.repository.PlaceRepository;
import com.javaacademy.cinema.repository.SessionRepository;
import com.javaacademy.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;

    public Session save(SessionDto sessionDto) {
        Session session = sessionMapper.dtoToSession(sessionDto);
        Session sessionAfterSave = sessionRepository.save(session);
        List<Place> placeList = placeRepository.selectAll();
        placeList.stream()
                .map(Place::getId)
                .map(placeId -> new Ticket(null,
                        placeRepository.findById(placeId).orElse(null),
                        sessionRepository.findById(sessionAfterSave.getId()).orElse(null),
                        false))
                .forEach(ticketRepository::save);

        return sessionAfterSave;
    }
}
