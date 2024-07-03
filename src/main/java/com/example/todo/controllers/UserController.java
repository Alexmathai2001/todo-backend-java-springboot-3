package com.example.todo.controllers;

import com.example.todo.entity.User;
import com.example.todo.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @PostMapping("/sign_in")
    public String signIn(@RequestBody User user, HttpSession session){
        return userService.loginUser(user,session);
    }

    @GetMapping("/user_info")
    public String userInfo() {
        return userService.getUserInfo(httpSession);
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
