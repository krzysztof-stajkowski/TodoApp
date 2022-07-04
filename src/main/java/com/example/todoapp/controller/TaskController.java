package com.example.todoapp.controller;


import com.example.todoapp.model.TaskRepository;
import com.example.todoapp.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository; //aby to działało z poza pakietu model musi być SqlTaskRepository interfejs public

    //kiedyś trzeba było dodawać @Autowired ale nie jest to już potrzebne
    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
        //dzieki temu nadpisaniu akcje na /tasks będą miały prostrzy zapis w postmanie ale bez HATEOAS i dodatkowo info w loggerze ALE tracimy możliwość sortowania w PostManie
        //dlatego dodajemy parametr params gdzie wykluczymy te Hateoasy aby one działały
    ResponseEntity<List<Task>> readAllTasks() { //zamieniam <?> na <List<Task> bo findall zwraca listę -> klasę Task trzeba zrobić publiczną aby zaimportować Listę od Task
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    //przeciążam metodę o tej samej nazwie dodając parametr Pageable, w którym możemy pobierać wiele informacji o stronicowaniach, numery stron itp
    @GetMapping(value = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent()); //trzeba aby nie było błędu pobrac GetContent bo page nie jest listą
    }

    @GetMapping("tasks/{id}")
    ResponseEntity<Task> readTask(@PathVariable int id) {
        return repository.findById((id))
                .map(task -> ResponseEntity.ok(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate) {
        Task result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @PutMapping("tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> { //to jest przykład ręcznego "ominięcia" adnotacji @Transactional ale zaleca się używac adnotacji
                    task.updateFrom(toUpdate);
                    repository.save(task);
                });
        return ResponseEntity.noContent().build();
    }

    @Transactional
    //metoda musi być public ; na początku metody ma być transaction begin a na końcu transaction commit do bazy danych |Hibernate ORM
    @PatchMapping("tasks/{id}")
    //w Postmanie wywołując metodę Patch zmienia się Done z tru na false i odwrotnie przy wybranym ID np. tasks/2
    public ResponseEntity<?> toggleTask(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }

}
