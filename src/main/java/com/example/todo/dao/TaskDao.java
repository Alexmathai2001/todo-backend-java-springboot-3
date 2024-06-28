package com.example.todo.dao;

import com.example.todo.repo.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
}
