package ru.georgdeveloper.taskapp.models;

import ru.georgdeveloper.taskapp.enums.Status;

import java.time.LocalDateTime;
import java.util.Collection;


public interface Task {
    void setNameTask(String nameTask);

    void setTaskBody(String taskBody);

    Collection<Status> getStatus();


    Collection<User> getExecutors();

    void setDateOfCreated(LocalDateTime now);

    Long getId();

    String getNameTask();

    String getTaskBody();

    void setStatus(Collection<Status> status);
}
