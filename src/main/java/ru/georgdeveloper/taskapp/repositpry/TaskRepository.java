package ru.georgdeveloper.taskapp.repositpry;

import ru.georgdeveloper.taskapp.enums.Status;
import ru.georgdeveloper.taskapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByNameTask(String name);
    List<Task> findTaskByStatus(Status status);
}
