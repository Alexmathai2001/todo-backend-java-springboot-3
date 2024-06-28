package com.example.todo.services;

import com.example.todo.dao.ProjectDao;
import com.example.todo.repo.Project;
import com.example.todo.repo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    public ResponseEntity<List<Project>> getProjects(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        return new ResponseEntity<>(projectDao.findByUserid(loggedInUser), HttpStatus.OK);
    }

    public ResponseEntity<String> postProject(HttpSession session,Project project) {
        User user=new User();
        user.setUserid((String) session.getAttribute("loggedInUser"));
        project.setUser(user);
        projectDao.save(project);
        return new ResponseEntity<>("successfull posted",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Project>> getProjectDetails(HttpSession session, int projectid) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }
}
