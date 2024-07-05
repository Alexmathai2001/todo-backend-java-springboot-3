package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "projectdata")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int projectid;
    private String projectname;
    private String projectdesc;
    private Date creationdate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> task;

}
