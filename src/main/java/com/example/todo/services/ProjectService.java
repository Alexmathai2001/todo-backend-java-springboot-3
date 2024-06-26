package com.example.todo.services;

import com.example.todo.dao.ProjectDao;
import com.example.todo.repo.Project;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    public ResponseEntity<List<Project>> getProjects(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        return new ResponseEntity<>(projectDao.findAll(), HttpStatus.OK);
    }
}
