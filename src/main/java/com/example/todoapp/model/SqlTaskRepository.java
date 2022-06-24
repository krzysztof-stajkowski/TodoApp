package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Wcześniej miałem @RepositoryRestResource. Samo Repository pozwala na więcej
 //(path = "todos", collectionResourceRel = "todos") //w nawiasach HATEOAS - jeśli zmienię path na cokolwiek to nie będzie już działać tasks tylko ta nowa nazwa, oraz drugi parametr zmieni nazwę wyświetlana w response w body
//informujemy Springa aby wiedział że ma z tego korzystać
@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> { //jpa repository tłumaczy zapytania na sql
}
