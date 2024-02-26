package ru.georgdeveloper.taskapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.georgdeveloper.taskapp.enums.Status;
import ru.georgdeveloper.taskapp.models.RegularTask;
import ru.georgdeveloper.taskapp.models.Task;
import ru.georgdeveloper.taskapp.models.UrgentTask;
import ru.georgdeveloper.taskapp.models.User;
import ru.georgdeveloper.taskapp.repositpry.TaskRepository;
import ru.georgdeveloper.taskapp.repositpry.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public boolean createTask(String nameTask, String taskBody, String status, long userId) {

        TaskFactory taskFactory = null;

        if (taskRepository.findByNameTask(nameTask) != null) return false;

        if (status.equals("urgent")) {
            taskFactory = new UrgentTaskFactory();

        } else if (status.equals("regular")) {
            taskFactory = new RegularTaskFactory();

        }

        Task task =  taskFactory.createTask();

        task.setNameTask(nameTask);
        task.setTaskBody(taskBody);
        task.getStatus().add(Status.NOT_STARTED);
        task.setStatus(task.getStatus());
        task.getExecutors().add(userRepository.findById(userId).orElse(null));
        task.setDateOfCreated(LocalDateTime.now());


        taskRepository.save((RegularTask) task);

        log.info("Task {} has been successfully created", task.getId());

        return true;
    }


    public List<RegularTask> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(String status) {

        System.out.println(status);

        return taskRepository.findTaskByStatus(Status.valueOf(status));

    }

    public boolean dellTask(Long id) {

        taskRepository.deleteById(id);
        log.info("Task {} has been successfully deleted", id);

        return true;
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public boolean updateTask(Long id, String nameTask, String taskBody, String status, long userId) {
        Task task = taskRepository.findById(id).orElse(new RegularTask());

        if (!nameTask.isEmpty()) {
            task.setNameTask(nameTask);
        } else task.setNameTask(taskRepository.findById(id).get().getNameTask());

        if (!taskBody.isEmpty()) {
            task.setTaskBody(taskBody);
        }

        if (userId != 0) {
            task.getExecutors().add(userRepository.findById(userId).orElse(null));
        } else task.setTaskBody(taskRepository.findById(id).get().getTaskBody());

        if (!status.isEmpty()) {
            task.getStatus().clear();
            task.getStatus().add(Status.valueOf(status));
            task.setStatus(task.getStatus());
        }

        taskRepository.save((RegularTask) task);
        log.info("Task {} has been successfully updated", task.getId());

        return true;
    }


    public List<Task> findTaskByUser(User userByPrincipal) {

        return taskRepository.findTaskByExecutors(userByPrincipal);
    }


}
