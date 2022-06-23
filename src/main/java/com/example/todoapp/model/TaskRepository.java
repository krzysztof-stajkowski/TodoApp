package com.example.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //(path = "todos", collectionResourceRel = "todos") //w nawiasach HATEOAS - jeśli zmienię path na cokolwiek to nie będzie już działać tasks tylko ta nowa nazwa, oraz drugi parametr zmieni nazwę wyświetlana w response w body
//informujemy Springa aby wiedział że ma z tego korzystać
interface TaskRepository extends JpaRepository<Task, Integer> {
}
