package ru.georgdeveloper.taskapp.services;

import ru.georgdeveloper.taskapp.models.RegularTask;
import ru.georgdeveloper.taskapp.models.Task;

public class RegularTaskFactory implements TaskFactory {
    @Override
    public Task createTask() {
        return new RegularTask();
    }
}
