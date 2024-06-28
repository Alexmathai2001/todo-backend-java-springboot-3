package com.example.todo.services;

import com.example.todo.dao.TaskDao;
import com.example.todo.repo.Task;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskDao taskdao;
    public ResponseEntity<String> addtask(HttpSession session, Task task) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        taskdao.save(task);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }
}
