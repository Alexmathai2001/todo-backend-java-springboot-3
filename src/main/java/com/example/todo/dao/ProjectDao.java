package com.example.todo.dao;

import com.example.todo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project,Integer> {
    @Query(value = "SELECT * FROM projectdata q WHERE q.user_id=:loggedInUser",nativeQuery = true)
    List<Project> findByUserid(String loggedInUser);
}
