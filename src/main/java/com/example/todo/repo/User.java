package com.example.todo.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private String user_id;
    private String user_name;
    private String email;
    private String password;

}