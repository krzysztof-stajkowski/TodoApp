package com.example.todoapp.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
abstract public class BaseAuditbableEntity {
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
        //funkcja się odpali przed zapisem do bazy. Za każdym razem gdy się jakaś encja zapisze to wtedy zapisze się data z tej funkcji
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
        //uruchamia się przed aktualizacją bazy
    void preMerge(){
        updatedOn = LocalDateTime.now();
    }
}
