package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource //(path = "todos", collectionResourceRel = "todos") //w nawiasach HATEOAS - jeśli zmienię path na cokolwiek to nie będzie już działać tasks tylko ta nowa nazwa, oraz drugi parametr zmieni nazwę wyświetlana w response w body
//informujemy Springa aby wiedział że ma z tego korzystać
interface TaskRepository extends JpaRepository<Task, Integer> { //jpa repository tłumaczy zapytania na sql

    @Override
    @RestResource(exported = false) //nadpisaliśmy metodę i usunęliśmy możliwość kasowania (musze być obydwie - info w dokumentacji Spring)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task entity);

    //możemy dodawać własne metody np. znajdź wszystkie taski zrobione (done = true)
    @RestResource(path="done",rel="done") //dodaję done aby w adresie nie było nazwy metody tylko done
    List<Task> findByDone(@Param("state") boolean done);

}
