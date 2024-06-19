package com.example.todo.services;

import com.example.todo.dao.UserDao;
import com.example.todo.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public ResponseEntity<String> addUser(User user){
        String userid = user.getUser_id();
        Optional<User> OptionalUser = userDao.findUserById(userid);
        if(OptionalUser.isPresent()){
            System.out.println("present");
            return new ResponseEntity<>("user already exist", HttpStatus.OK);
        }else {
            String userID = generateUserID();
            String password = user.getPassword();
            user.setPassword(hashString(password));
            user.setUser_id(userID);
            userDao.save(user);
            return new ResponseEntity<>("user created"+userID, HttpStatus.OK);
        }
    }

    private String generateUserID() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000)+1000;
        return "USR-"+randomNumber;
    }

    public static String hashString(String input){
        try {
            System.out.println("enterd first function");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            System.out.println(hash);
            return byteToHash(hash);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    private static String byteToHash(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for(byte b : hash){
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
            System.out.println("entered second function");
        }
        return hexString.toString();
    }
}
