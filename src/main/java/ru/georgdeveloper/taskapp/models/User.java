package ru.georgdeveloper.taskapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name= "age")
    private int age;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

}
