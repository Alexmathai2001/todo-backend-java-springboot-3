package com.example.todo.controllers;

import com.example.todo.repo.Project;
import com.example.todo.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("app")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    HttpSession session;
    @GetMapping("/getprojects")
    public ResponseEntity<List<Project>> getProjects(){
        if(session.getAttribute("loggedInUser") != null){
            return projectService.getProjects(session);
        }else{
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }
    }
@PostMapping("/addproject")
    public ResponseEntity<String> postProjects(@RequestBody Project project){
        if(session.getAttribute("loggedInUser") != null){
            return projectService.postProject(session,project);
        }else{
            return new ResponseEntity<>("Not logged in", HttpStatus.BAD_REQUEST);
        }
    }
}
