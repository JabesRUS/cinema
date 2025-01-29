package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketMapper {

    public TicketDto ticketToDto(Ticket ticket) {
        Integer ticketId = ticket.getId();
        String placeName = ticket.getPlace().getName();
        String movieName = ticket.getSession().getMovie().getName();
        LocalDateTime date = ticket.getSession().getDateTime();

        return new TicketDto(ticketId, placeName, movieName, date);
    }
}
