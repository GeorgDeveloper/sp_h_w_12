package ru.georgdeveloper.taskapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.georgdeveloper.taskapp.models.User;
import ru.georgdeveloper.taskapp.services.TaskService;
import ru.georgdeveloper.taskapp.services.UserService;

/**
 * @login - форма авторизации пользователя
 *
 * @registration - форма регистрации пользователя
 *
 * @createUser - создание пользователя
 *
 * @userInfo - информация о пользователе
 *
 */

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.findTaskByUser(user));
        return "user-info";
    }

}
