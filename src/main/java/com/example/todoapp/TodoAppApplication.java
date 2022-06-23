package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TodoAppApplication implements RepositoryRestConfigurer {

    public static void main(String[] args) {
        //to jest odpalane w tle na Tomcacie (localhost 8080)
        SpringApplication.run(TodoAppApplication.class, args);
    }

    //aby walidacja nie dawała błędu 500 należy zaimplementować RepositoryRestConfigurer oraz Overridować poniższy configureValidatingRepositoryEventListener
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        RepositoryRestConfigurer.super.configureValidatingRepositoryEventListener(validatingListener);
        //powyższa linia jest automatycznie wstawiona'
        //poniższe są dopisane
        validatingListener.addValidator("beforeCreate", validator()); //validator to jest metoda którą sami zaraz tworzymy
        validatingListener.addValidator("beforeSave", validator());
    }

    //customowa metoda - musi mieć adnotację @Bean
    @Bean //Spring będzie sam tym dzięki temu zarządzał
    Validator validator() { //bez modyfikatora dostepu
        return new LocalValidatorFactoryBean();
    }
}
