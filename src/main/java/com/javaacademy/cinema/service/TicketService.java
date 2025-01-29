package com.javaacademy.cinema.service;

import com.javaacademy.cinema.dto.BookingDto;
import com.javaacademy.cinema.dto.TicketDto;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.mapper.TicketMapper;
import com.javaacademy.cinema.repository.PlaceRepository;
import com.javaacademy.cinema.repository.SessionRepository;
import com.javaacademy.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;
    private final TicketMapper ticketMapper;

    public TicketDto bookingTicket(BookingDto bookingDto) {
        Integer sessionId = bookingDto.getSessionId();
        String namePlace = bookingDto.getPlaceName();

        Ticket foundTicket = Stream.concat(ticketRepository.getUnsoldTickets().stream(),
                        ticketRepository.getSoldTickets().stream())
                .filter(ticket -> ticket.getSession().getId().equals(sessionId))
                .filter(ticket -> ticket.getPlace().getName().equals(namePlace))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Билет по указанному имен не найден."));

        ticketRepository.changeStatus(foundTicket.getId());

        return ticketMapper.ticketToDto(foundTicket);

//        Optional<Place> findPlace = placeRepository.selectAll().stream()
//                .filter(place -> place.getName().equals(namePlace))
//                .findFirst();
//        if (findPlace.isEmpty()){
//            throw new RuntimeException("Место не найдено.")
//        }
//
//        Integer placeid = findPlace.get().getId();


//        ticketRepository.getUnsoldTickets().stream()
//                .filter(ticket -> ticket.getSession().getId().equals(sessionId))
//                .filter(ticket -> ticket.getPlace().getName().equals(namePlace))
//                .findFirst()
//                .ifPresent(ticket ->
//                        ticketRepository.changeStatus(ticket.getId()));

    }

    public List<Ticket> getSoldTicket() {
        return ticketRepository.getSoldTickets();
    }

}
