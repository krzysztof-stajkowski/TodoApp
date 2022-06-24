package com.example.todoapp.controller;


import com.example.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/tasks", params = {"!sort","!page","!size"}) //dzieki temu nadpisaniu akcje na /tasks będą miały prostrzy zapis w postmanie ale bez HATEOAS i dodatkowo info w loggerze ALE tracimy możliwość sortowania w PostManie
                            //dlatego dodajemy parametr params gdzie wykluczymy te Hateoasy aby one działały
    ResponseEntity<?> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    //przeciążam metodę o tej samej nazwie dodając parametr Pageable, w którym możemy pobierać wiele informacji o stronicowaniach, numery stron itp
    @GetMapping(value = "/tasks")
    ResponseEntity<?> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page));
    }

}
