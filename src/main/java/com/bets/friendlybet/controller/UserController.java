package com.bets.friendlybet.controller;

import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    TODO: Update account for a user, not only admin, but not other users
    @PutMapping(path = "/{userId}")
    public void updateUser(@RequestBody UserDTO user){
        userService.updateUser(user);
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO user){
        userService.createUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }
}
