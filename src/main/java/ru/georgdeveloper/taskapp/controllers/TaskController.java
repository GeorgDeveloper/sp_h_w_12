package ru.georgdeveloper.taskapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.georgdeveloper.taskapp.models.Task;
import ru.georgdeveloper.taskapp.services.TaskService;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @PostMapping("/create")
    public String createTask(Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String findTasks(@RequestParam(name = "stat", required = false) String status, Model model) {
        model.addAttribute("tasks", taskService.findByStatus(status));
        return "task_table";
    }

    @PostMapping("task_table/delete/{id}")
    public String dellTasks(@PathVariable Long id) {
        taskService.dellTask(id);
        return "redirect:/";
    }

    @PostMapping("task_table/change/{id}")
    public String findTasksById(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "task_change";
    }

    @PostMapping("update/{id}")
    public String updateTasksById(@PathVariable Long id, @RequestParam(required = false) String nameTask,
                                  @RequestParam(required = false) String taskBody,
                                  @RequestParam(required = false) String status) {
        taskService.updateTask(id, nameTask, taskBody, status);
        return "redirect:/";
    }
}
