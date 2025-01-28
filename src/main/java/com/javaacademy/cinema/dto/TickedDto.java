package com.javaacademy.cinema.dto;

import com.javaacademy.cinema.entity.Place;
import com.javaacademy.cinema.entity.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TickedDto {
    private Integer id;
    private Place place;
    private Session session;
    private Boolean isBought;
}
