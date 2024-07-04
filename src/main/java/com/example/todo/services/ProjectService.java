package com.example.todo.services;

import com.example.todo.dao.ProjectDao;
import com.example.todo.dao.TaskDao;
import com.example.todo.entity.Project;
import com.example.todo.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;
    @Autowired
    TaskDao taskdao;
    public ResponseEntity<List<Project>> getProjects(HttpSession session) {

        String loggedInUser = (String) session.getAttribute("loggedInUser");
        List<Project> projects=projectDao.findByUserid(loggedInUser);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    public ResponseEntity<String> postProject(HttpSession session,Project project) {
        User user=new User();
        user.setUserid((String) session.getAttribute("loggedInUser"));
        project.setUser(user);
        projectDao.save(project);
        return new ResponseEntity<>("successfull posted",HttpStatus.CREATED);
    }

    public ResponseEntity<Project> getProjectDetails(HttpSession session, int projectid) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        return new ResponseEntity<>(projectDao.findAllById(Collections.singleton(projectid)).get(0), HttpStatus.BAD_REQUEST);
    }

    public Project updateProject(Project project, HttpSession session) {
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            Optional<Project> dbProject = projectDao.findById(project.getProjectid());
            if (dbProject.isPresent()) {
                Project newProject = dbProject.get();
                newProject.setProjectname(project.getProjectname());
                newProject.setProjectdesc(project.getProjectdesc());
                newProject.setCreationdate(project.getCreationdate());
                System.out.println("in");
                System.out.println(newProject);
                return projectDao.save(newProject);
            }else {
                throw  new RuntimeException(project.getProjectname()+" project doesnot exist!");
            }
    }

    public ResponseEntity<?> deleteProject(int projectid, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        Optional<Project> dbproject = projectDao.findById(projectid);
        if(dbproject.isPresent()){
            Project existingProject = dbproject.get();
            if(!existingProject.getUser().getUserid().equals(loggedInUser)){
                System.out.println("unautorized");
                return ResponseEntity.status(401).body("unautorized request");
            }
            projectDao.delete(existingProject);
            System.out.println("deleted");
            return ResponseEntity.status(200).body("The project was deleted successfully");
        }else {
            return ResponseEntity.status(404).body("project not found");
        }

    }
}
