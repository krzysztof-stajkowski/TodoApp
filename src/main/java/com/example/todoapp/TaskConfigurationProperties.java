package com.example.todoapp;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private boolean allowMultipleTasksFromTempate;

   public boolean isAllowMultipleTasksFromTempate(){
        return allowMultipleTasksFromTempate;
    }

    public void setAllowMultipleTasksFromTempate(boolean allowMultipleTasksFromTempate) {
        this.allowMultipleTasksFromTempate = allowMultipleTasksFromTempate;
    }


}
