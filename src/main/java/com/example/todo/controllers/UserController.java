package com.example.todo.controllers;

import com.example.todo.repo.User;
import com.example.todo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign_in")
    public String signIn(@RequestBody User user, HttpSession session){
        return userService.loginUser(user,session);
    }

    @GetMapping("/user_info")
    public String userInfo(HttpSession session) {
        return userService.getUserInfo(session);
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session){
        return userService.logout(session);
    }


    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestBody User user){
        return userService.addUser(user);
    }


}
