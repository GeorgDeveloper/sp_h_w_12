package ru.georgdeveloper.taskapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.georgdeveloper.taskapp.enums.Status;
import ru.georgdeveloper.taskapp.models.Task;
import ru.georgdeveloper.taskapp.repositpry.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public boolean createTask(Task task) {
        if (taskRepository.findByNameTask(task.getNameTask()) != null) return false;
        task.getStatus().add(Status.NOT_STARTED);
        task.setStatus(task.getStatus());
        task.setDateOfCreated(LocalDateTime.now());
        taskRepository.save(task);
        log.info("Task {} has been successfully created", task.getId());
        return true;
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(String status) {
        System.out.println(status);
        return taskRepository.findTaskByStatus(Status.valueOf(status));

    }

    public void dellTask(Long id) {
        taskRepository.deleteById(id);
        log.info("Task {} has been successfully deleted", id);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public boolean updateTask(Long id, String nameTask, String taskBody, String status) {
        Task task = taskRepository.findById(id).orElse(new Task());
        if (!nameTask.isEmpty()) {
            task.setNameTask(nameTask);
        } else task.setNameTask(taskRepository.findById(id).get().getNameTask());
        if (!taskBody.isEmpty()){
            task.setTaskBody(taskBody);
        } else task.setTaskBody(taskRepository.findById(id).get().getTaskBody());
        if (!status.isEmpty()){
            task.getStatus().clear();
            task.getStatus().add(Status.valueOf(status));
            task.setStatus(task.getStatus());
        }

        taskRepository.save(task);
        log.info("Task {} has been successfully updated", task.getId());
        return true;
    }
}
