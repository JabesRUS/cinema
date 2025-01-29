package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.BookingDto;
import com.javaacademy.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TickedController {
    private final TicketService ticketService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> bookingTicket(@RequestBody BookingDto bookingDto) {
        try {
            return ResponseEntity.ok(ticketService.bookingTicket(bookingDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access Denied");
        }

    }

    @GetMapping("/saled")
    public ResponseEntity<?> findSoldTicket(@RequestHeader(value = "user-token", required = true) String userToken) {
        if (SECRET_TOKEN.equals(userToken)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ticketService.getSoldTicket());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied");
        }
    }
}
