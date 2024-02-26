package ru.georgdeveloper.taskapp.repositpry;

import ru.georgdeveloper.taskapp.enums.Status;
import ru.georgdeveloper.taskapp.models.RegularTask;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.georgdeveloper.taskapp.models.Task;
import ru.georgdeveloper.taskapp.models.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<RegularTask,Long> {
    RegularTask findByNameTask(String name);
    List<Task> findTaskByStatus(Status status);
    List<Task> findTaskByExecutors(User userByPrincipal);
}
