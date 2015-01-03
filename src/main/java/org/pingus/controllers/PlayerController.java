package org.pingus.controllers;

import org.pingus.dao.UserDao;
import org.pingus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final UserDao userDao;

    @Autowired
    public PlayerController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/register")
    public String register(@RequestBody User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        try {
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }
}
