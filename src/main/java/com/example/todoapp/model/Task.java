package com.example.todoapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //zmieniłem z AUTO na IDENTITY - sugestia trenera w tym przypadku zmian ustawień w bazie danych które będę robić później
    private int id;
    @NotBlank(message = "Tasks description must not be empty")
    private String description;
    private boolean done;

    //pusty konstruktor aby hibernate nie miał kiedyś problemów przy zmianie repozytorium w interfejsie (adnotacji)
    public Task() {
    }

    //muszą być ustawione dostępy na public
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}