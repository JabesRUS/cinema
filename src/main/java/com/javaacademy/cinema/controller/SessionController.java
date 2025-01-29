package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.service.SessionService;
import lombok.AllArgsConstructor;
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
public class SessionController {
    private final SessionService sessionService;
    private static final String SECRET_TOKEN = "secretadmin123";

    @GetMapping("/{idSession}/free-place")
    public List<String> getFreePlace(@PathVariable Integer idSession) {
        return sessionService.getFreePlace(idSession);
    }

    @GetMapping
    public List<Session> selectAll() {
        return sessionService.selectAll();
    }

    @PostMapping
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
