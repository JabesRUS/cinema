package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.SessionDto;
import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SessionMapper {
    public static final String MESSAGE_NOT_FOUND_MOVIE = "Фильм по указанному id-%s не найден.";
    private final MovieRepository movieRepository;
    public Session dtoToSession(SessionDto sessionDto) {
        Movie movie = movieRepository.findById(sessionDto.getMovieID())
                .orElseThrow(() -> new NoSuchElementException(MESSAGE_NOT_FOUND_MOVIE.formatted(sessionDto.getMovieID())));
        return new Session(null,
                movie,
                sessionDto.getDateTime(),
                sessionDto.getPrice());
    }
}
