package com.banking.ledger.usermanager.controllers;

import com.banking.ledger.usermanager.beans.User;
import com.banking.ledger.usermanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity getUserById(@PathVariable(value = "userId") int userId) {
        try {
            User user = userService.getUserById(userId);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public User createBank(@RequestBody User user) {
        return userService.createUser(user);
    }
}
