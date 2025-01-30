package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO билета при его успешной покупке, содержит id билета," +
        " номер места, название фильма, дату сеанса")
public class TicketDto {
    @Schema(description = "id билета")
    private Integer id;
    @JsonProperty("place_name")
    @Schema(description = "номер места")
    private String placeName;
    @JsonProperty("movie_name")
    @Schema(description = "название фильма")
    private String movieName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "дата и время сеанса")
    private LocalDateTime date;
}
