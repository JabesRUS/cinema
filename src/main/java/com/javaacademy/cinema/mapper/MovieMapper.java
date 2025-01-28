package com.javaacademy.cinema.mapper;

import com.javaacademy.cinema.dto.MovieDto;
import com.javaacademy.cinema.entity.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    public Movie dtoToMovie(MovieDto movieDto) {
        return new Movie(null,
                movieDto.getName(),
                movieDto.getDescription());
    }
}
