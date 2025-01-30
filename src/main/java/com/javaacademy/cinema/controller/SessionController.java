package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.service.SessionService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
@Tag(name = "Сеансы", description = "Контроллер по работе с сеансами.")
public class SessionController {
    private final SessionService sessionService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @GetMapping("/{idSession}/free-place")
    @Operation(summary = "Показать свободные места на сеанс.",
            description = "Возвращает список свободных мест по переданному id сеанса.",
    responses = @ApiResponse(responseCode = "200", description = "Список свободных мест",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))))
    public List<String> getFreePlace(@PathVariable Integer idSession) {
        return sessionService.getFreePlace(idSession);
    }

    @GetMapping
    @Operation(summary = "Показать все сеансы", description = "Возвращает список всех сеансов",
            responses = @ApiResponse(responseCode = "200", description = "Список свободных мест",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Session.class)))))
    public List<Session> selectAll() {
        return sessionService.selectAll();
    }

    @PostMapping
    @Operation(summary = "Сохранить сеанс в БД",
            description = "Передаются id фильма, дата сеанса и цена билета, далее создается новый сеанс в БД",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Сеанс сохранен в БД",
                            content = @Content(schema = @Schema(implementation = Session.class))),
                    @ApiResponse(responseCode = "403", description = "В доступе отказано.")
            })
    public ResponseEntity<?> save(@RequestBody SessionDto sessionDto,
                                  @RequestHeader(value = "user-token", required = true) String userToken) {
        if (SECRET_TOKEN.equals(userToken)) {
            return ResponseEntity.ok(sessionService.save(sessionDto));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied");
        }
    }
}
