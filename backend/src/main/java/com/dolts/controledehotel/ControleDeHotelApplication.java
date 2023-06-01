package com.dolts.controledehotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ControleDeHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleDeHotelApplication.class, args);
    }

}
