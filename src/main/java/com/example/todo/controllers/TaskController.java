package com.example.todo.controllers;

import com.example.todo.repo.Project;
import com.example.todo.repo.Task;
import com.example.todo.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskservice;
    @Autowired
    HttpSession session;
    @GetMapping("/sample")
    public String sample(){

        return "test";
    }

    @PostMapping("/addtask")
    public ResponseEntity<String> addtask(@RequestBody Task task){
//        Project project=new Project();
//        project.setProjectid(task.getProject().getProjectid());
//        task.setProject(project);
//        System.out.println(task.toString());
        return taskservice.addtask(session,task);
    }
}
