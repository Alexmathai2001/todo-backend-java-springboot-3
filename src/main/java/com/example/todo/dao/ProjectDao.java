package com.example.todo.dao;

import com.example.todo.repo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends JpaRepository<Project,Integer> {
}
