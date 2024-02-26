package ru.georgdeveloper.taskapp.services;

import ru.georgdeveloper.taskapp.models.Task;
import ru.georgdeveloper.taskapp.models.UrgentTask;

public class UrgentTaskFactory implements TaskFactory {
    @Override
    public Task createTask() {
        return new UrgentTask();
    }
}

