package ru.georgdeveloper.taskapp.repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.georgdeveloper.taskapp.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
