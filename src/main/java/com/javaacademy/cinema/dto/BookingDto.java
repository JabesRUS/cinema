package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для покупки билета с данными о сеансе и месте")
public class BookingDto {
    @Schema(description = "id сессии")
    @JsonProperty("session_id")
    private Integer sessionId;
    @JsonProperty("place_name")
    @Schema(description = "номер места")
    private String placeName;
}
