package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "todo")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int taskid;
    private String taskname;
    private LocalDate creationdate;
    private boolean status;
    @ManyToOne
    @JoinColumn(name="projectid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Project project;

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", taskname='" + taskname + '\'' +
                ", creationdate=" + creationdate +
                ", status=" + status +
                ", project=" + project +
                '}';
    }
}
