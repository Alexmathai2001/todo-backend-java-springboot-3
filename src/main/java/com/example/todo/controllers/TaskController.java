package com.example.todo.controllers;

import com.example.todo.entity.Task;
import com.example.todo.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskservice;
    @Autowired
    HttpSession session;


    @PostMapping("/addtask")
    public ResponseEntity<String> addtask(@RequestBody Task task){
        return taskservice.addtask(session,task);
    }

    @GetMapping("/gettodo/{projectid}")
    public ResponseEntity<List  <Task>> gettasks(@PathVariable int projectid){

        return taskservice.getTask(projectid);
    }


    @PutMapping("/update_todo")
    public ResponseEntity<?> updateTodo(@RequestBody Task task){
        try {
            return taskservice.updateTodo(task,session);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
