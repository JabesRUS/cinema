package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TickedController {
    private final TicketService ticketService;

    @GetMapping("/saled")
    public ResponseEntity<?> findSoldTicket() {
        return ResponseEntity.ok(ticketService.getSoldTicket());
    }
}
