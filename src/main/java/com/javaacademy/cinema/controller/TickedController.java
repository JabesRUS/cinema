package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.BookingDto;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Билеты", description = "Контроллер по работе с билетами.")
public class TickedController {
    private final TicketService ticketService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Продажа билета",
            description = "Продажа билета по указанному id сеанса и номеру места",
            responses = {
                    @ApiResponse(responseCode = "203", description = "Билет куплен",
                            content = @Content(schema = @Schema(implementation = BookingDto.class))),
                    @ApiResponse(responseCode = "409", description = "Конфликт, билет продан")
            })
    public ResponseEntity<?> bookingTicket(@RequestBody BookingDto bookingDto) {
        try {
            return ResponseEntity.ok(ticketService.bookingTicket(bookingDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("CONFLICT");
        }

    }

    @GetMapping("/saled")
    @Operation(summary = "Показать купленные билеты",
            description = "Возвращает список купленных билетов.",
            responses = @ApiResponse(responseCode = "200", description = "Список купленных билетов",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ticket.class)))))
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
