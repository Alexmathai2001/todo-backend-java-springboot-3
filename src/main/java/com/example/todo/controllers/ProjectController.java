package com.example.todo.controllers;

import com.example.todo.repo.Project;
import com.example.todo.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("app")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping("/getprojects")
    public ResponseEntity<List<Project>> getProjects(HttpSession session){
        if(session.getAttribute("loggedInUser") != null){
            return projectService.getProjects(session);
        }else{
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }
    }
}
