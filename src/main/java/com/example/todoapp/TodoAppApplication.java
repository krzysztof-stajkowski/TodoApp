package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {
        //to jest odpalane w tle na Tomcacie (localhost 8080)
        SpringApplication.run(TodoAppApplication.class, args);
    }

    //customowa metoda - musi mieć adnotację @Bean
    @Bean //Spring będzie sam tym dzięki temu zarządzał
    Validator validator() { //bez modyfikatora dostepu
        return new LocalValidatorFactoryBean();
    }
}
