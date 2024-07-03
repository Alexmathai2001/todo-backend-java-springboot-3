package com.example.todo.dao;

import com.example.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
    List<Task> findByProjectProjectid(int projectid);
}
