package com.javaacademy.cinema.service;

import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> getSoldTicket() {
        return ticketRepository.getSoldTickets();
    }
}
