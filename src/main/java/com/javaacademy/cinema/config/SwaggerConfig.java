package com.javaacademy.cinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        Info info = new Info()
                .title("Api для кинотеатра")
                .contact(new Contact()
                        .name("Евгений\n")
                        .email("mail.mail.ru")
                        .url("https://goo.su/FNYy"))

                .description("""
                         Это API для управления кинотеатром. Предоставляет возможность администратору
                          кинотеатра создавать кино,
                         сеансы и смотреть проданные билеты.\n Посетитель же сможет смотреть какие фильмы
                          и сеансы в кинотеатре, а также покупать билеты."""
                        );
        return new OpenAPI().info(info);
    }
}
