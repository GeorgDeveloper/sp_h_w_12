package ru.georgdeveloper.taskapp.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.georgdeveloper.taskapp.services.TaskService;
import ru.georgdeveloper.taskapp.services.UserService;

import java.security.Principal;

/**
 * @tasks - отображение всех необходимых параметров
 *
 * @createTask - создание задачи
 *
 * @findTasks - найти все задачи по статусу
 *
 * @dellTasks - удалить задачу
 *
 * @findTasksById- найти задачу по id
 *
 * @chengTasksById - вызов формы изменения задачи
 *
 * @updateTasksById - изменение задачи
 */


@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/")
    public String tasks(Principal principal, Model model) {
        model.addAttribute("userIsActiveRole", userService.getUserByPrincipal(principal).isAdmin());
        model.addAttribute("executors", userService.findAll());
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("tasksUser", taskService.findTaskByUser(userService.getUserByPrincipal(principal)));
        return "index";
    }

    @PostMapping("/create")
    public String createTask(@RequestParam("nameTask") String nameTask, @RequestParam("taskBody") String taskBody,@RequestParam("user") long userId) {
        taskService.createTask(nameTask, taskBody, userId);
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

    @PostMapping("task_table/find/{id}")
    public String findTasksById(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "task_change";
    }


    @PostMapping("update/{id}")
    public String updateTasksById(@PathVariable Long id, @RequestParam(required = false) String nameTask,
                                  @RequestParam(required = false) String taskBody,
                                  @RequestParam(required = false) String status,@RequestParam(name = "user", required = false) long userId , Model model) {
        model.addAttribute("executors", userService.findAll());
        taskService.updateTask(id, nameTask, taskBody, status, userId);
        return "redirect:/";
    }


    @PostMapping("task_table/change/{id}")
    public String chengTasksById(@PathVariable Long id, Model model) {
        model.addAttribute("executors", userService.findAll());
        model.addAttribute("task", taskService.findTaskById(id));
        return "task_change";
    }
}