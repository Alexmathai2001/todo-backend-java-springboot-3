package com.example.todo.services;

import com.example.todo.dao.UserDao;
import com.example.todo.repo.User;
import jakarta.servlet.http.HttpSession;
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
        String emailId = user.getEmail();
        Optional<User> OptionalUser = userDao.findUserByEmail(emailId);
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

    //helper function to generate a random userid
    private String generateUserID() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000)+1000;
        return "USR-"+randomNumber;
    }

    //helper functions to hash the password
    public static String hashString(String input){
        try {
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
        }
        return hexString.toString();
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String hashedInput = hashString(inputPassword);
        return hashedInput.equals(storedHash);
    }


    public String getUserInfo(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return "Logged in as: " + loggedInUser;
        } else {
            return "Not logged in";
        }
    }

    public String loginUser(User user, HttpSession session) {
        String email = user.getEmail();
        System.out.println(email);
        Optional<User> optionalUser = userDao.findUserByEmail(email);
        if(optionalUser.isPresent()){
            String userTypedPassword = user.getPassword();
            String dbPassword = optionalUser.get().getPassword();
            if(verifyPassword(userTypedPassword, dbPassword)){
                String useridFromDb = optionalUser.get().getUser_id();
                session.setAttribute("loggedInUser",useridFromDb );
                return "user found and password is matched";

            }else{
                return "user found but password is not correct";
            }
        }else {
            return "user not found";
        }
    }

    public String logout(HttpSession session) {
        //checking if a session is present or not
        if(session.getAttribute("loggedInUser") != null){
            //destroying session
            session.invalidate();
            return "Logout made successfull";
        }else {
            return "No one is logged in";
        }
    }
}
