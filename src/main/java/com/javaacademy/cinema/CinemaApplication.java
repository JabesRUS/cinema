package com.javaacademy.cinema;

import com.javaacademy.cinema.entity.Movie;
import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import com.javaacademy.cinema.entity.Ticket;
import com.javaacademy.cinema.repository.MovieRepository;
import com.javaacademy.cinema.repository.SessionRepository;
import com.javaacademy.cinema.repository.TicketRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(CinemaApplication.class, args);
//        MovieRepository movieRepository = context.getBean(MovieRepository.class);
//        SessionRepository sessionRepository = context.getBean(SessionRepository.class);
//        TicketRepository ticketRepository = context.getBean(TicketRepository.class);
//        Movie movie = new Movie(null, "Новый фильм", "Описание фильма");
//        movieRepository.save(movie);
//        System.out.println("_____");
//        System.out.println(movieRepository.selectAll());
//        System.out.println("_____");
//        System.out.println("_____");
//
//        Session session = new Session(null, movie, LocalDateTime.now(), BigDecimal.valueOf(11111));
//        System.out.println(sessionRepository.save(session));
//        System.out.println("_____");
//        System.out.println("_____");
//        System.out.println(sessionRepository.selectAll());
//        System.out.println("_____");
//
//        Place place = new Place(1, "F1");
//        Ticket ticket = new Ticket(null, place, session, false);
//        System.out.println(ticketRepository.save(ticket));
//        System.out.println("_____");
//        System.out.println(ticketRepository.findById(5));
////        ticketRepository.changeStatus(5);
//        System.out.println(ticketRepository.findById(5));
//
//        System.out.println(ticketRepository.getSoldTickets());
//        System.out.println(ticketRepository.getUnsoldTickets());

    }

}
