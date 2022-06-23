package com.example.todoapp.controller;

import com.example.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
 class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository; //aby to działało z poza pakietu model musi być TaskRepository interfejs public
//kiedyś trzeba było dodawać @Autowired ale nie jest to już potrzebne
    TaskController(final TaskRepository repository) {
        this.repository = repository;
    }
}
