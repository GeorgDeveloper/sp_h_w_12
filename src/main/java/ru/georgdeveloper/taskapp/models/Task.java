package ru.georgdeveloper.taskapp.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.georgdeveloper.taskapp.enums.Status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_task")
    private String nameTask;
    @Column(name = "task_body", length = 1000)
    private String taskBody;
    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_status", joinColumns = @JoinColumn(name = "task_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status = new HashSet<>();
    @Column(name = "dateOfCreated")
    private LocalDateTime dateOfCreated;


}
