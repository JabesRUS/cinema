package com.javaacademy.cinema.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания фильма, содержит название и описание фильма")
public class MovieDto {
    @Schema(description = "название фильма")
    private String name;
    @Schema(description = "описание фильма")
    private String description;
}
