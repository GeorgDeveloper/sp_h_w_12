package ru.georgdeveloper.taskapp.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.georgdeveloper.taskapp.enums.Status;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
public class RegularTask implements Task{
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_executors",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> executors = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getTaskBody() {
        return taskBody;
    }

    @Override
    public void setStatus(Collection<Status> status) {
        this.status = (Set<Status>) status;
    }

    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public Set<User> getExecutors() {
        return executors;
    }

    public void setExecutors(Set<User> executors) {
        this.executors = executors;
    }

}
