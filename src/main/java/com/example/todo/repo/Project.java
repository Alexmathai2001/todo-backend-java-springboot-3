package com.example.todo.repo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "projectdata")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int projectid;
    private String userid;
    private String projectname;
    private String projectdesc;
    private Date creationdate;

}
