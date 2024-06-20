package com.example.todo.dao;

import com.example.todo.repo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "Select * from users where email=:emailId",nativeQuery = true)
    Optional<User> findUserByEmail(String emailId);
}
