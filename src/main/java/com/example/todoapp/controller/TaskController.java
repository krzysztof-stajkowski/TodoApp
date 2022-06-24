package com.example.todoapp.controller;


import com.example.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository; //aby to działało z poza pakietu model musi być TaskRepository interfejs public

    //kiedyś trzeba było dodawać @Autowired ale nie jest to już potrzebne
    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks") //dzieki temu nadpisaniu akcje na /tasks będą miały prostrzy zapis w postmanie ale bez HATEOAS i dodatkowo info w loggerze
    ResponseEntity<?> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

}
