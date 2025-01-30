package com.javaacademy.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания сеанса, содержит фильм, дату и цену сеанса")
public class SessionDto {
    @JsonProperty("movie_id")
    @Schema(description = "id фильма")
    private Integer movieID;

    @JsonProperty("data_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "дата и время сеанса")
    private LocalDateTime dateTime;
    @Schema(description = "цена билета")
    private BigDecimal price;
}
