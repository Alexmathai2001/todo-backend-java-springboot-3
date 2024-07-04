package com.example.todo.controllers;

import com.example.todo.entity.Project;
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
    @GetMapping("/getproject/{projectid}")
    public ResponseEntity<Project> getProjectDetails(@PathVariable int projectid){

        if(session.getAttribute("loggedInUser") != null){
            return projectService.getProjectDetails(session,projectid);
        }else{
            return new ResponseEntity<>(new Project(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_project")
    public ResponseEntity<?> updateproject(@RequestBody Project project){
        try {
            if(session.getAttribute("loggedInUser") != null){
                Project projectRes = projectService.updateProject(project,session);
                return ResponseEntity.status(200).body(projectRes);
            }else{
                return ResponseEntity.status(201).body("user not logged in. Please try again after logging in!");
            }

        }catch (RuntimeException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_project/{projectid}")
    public ResponseEntity<?> updateproject(@PathVariable int projectid){
        try {
            if(session.getAttribute("loggedInUser") != null){

                return ResponseEntity.status(200).body(projectService.deleteProject(projectid,session));
            }else{
                return ResponseEntity.status(201).body("user not logged in. Please try again after logging in!");
            }

        }catch (RuntimeException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
