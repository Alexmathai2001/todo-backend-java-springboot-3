package com.example.todo.repo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String project_id;
    private String user_id;
    private String project_name;
    private String project_desc;
    private Date creation_date;

}
