package com.example.todo.services;

import com.example.todo.dao.TaskDao;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskDao taskdao;
    public ResponseEntity<String> addtask(HttpSession session, Task task) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        taskdao.save(task);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Task>> getTask(int projectid) {

        return new ResponseEntity<>(taskdao.findByProjectProjectid(projectid),HttpStatus.OK);
    }

    public ResponseEntity<?> updateTodo(Task task, HttpSession session) {

        Optional <Task> DBtask = taskdao.findById(task.getTaskid());
        String sessionData = (String) session.getAttribute("loggedInUser");

        if(DBtask.isPresent()){
//            Project newproj = task.getProject();
//            String user = newproj.getUser().getUserid();
            DBtask.get().setTaskname(task.getTaskname());
            DBtask.get().setCreationdate(task.getCreationdate());
            DBtask.get().setStatus(task.isStatus());
            taskdao.save(DBtask.get());


            return ResponseEntity.status(200).body("task updation successfull");
        }
        return ResponseEntity.status(404).body("task not found");
    }
}
