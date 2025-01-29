package com.javaacademy.cinema.controller;


import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.service.MovieService;
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
public class MovieController {
    private final MovieService movieService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @GetMapping
    public List<MovieDto> selectAll() {
        return movieService.selectAll();
    }

    @PostMapping
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
