package com.javaacademy.cinema.controller;


import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.service.MovieService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Tag(name = "Фильмы", description = "Контроллер по работе с фильмами.")
public class MovieController {
    private final MovieService movieService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @GetMapping
    @Operation(summary = "Получение все фильмы.", description = "Возвращает список всех фильмов.",
    responses = {
            @ApiResponse(responseCode = "200", description = "Список возвращен",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))))
    })
    public List<MovieDto> selectAll() {
        return movieService.selectAll();
    }

    @PostMapping
    @Operation(summary = "Сохранение фильма в БД",
            description = "Название и описание фильма сохраняются в БД.",
    responses = {
            @ApiResponse(responseCode = "201", description = "Фильм сохранен",
            content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "403", description = "В доступе отказано.")
    })
    public ResponseEntity<?> save(@RequestHeader(value = "user-token", required = true) String userToken,
                                     @RequestBody MovieDto movieDto) {
        if (SECRET_TOKEN.equals(userToken)) {
//            return ResponseEntity.ok(movieService.save(movieDto));
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(movieService.save(movieDto));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body("Access denied");
        }
    }
}
