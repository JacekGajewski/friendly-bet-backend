package com.bets.friendlybet.controller;

import com.bets.friendlybet.dto.PasswordDTO;
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

    //    If you want to lock the user for some time after number of tries you should change PUT to POST
    @PutMapping(path = "/{userId}")
    public void updateUser(@RequestBody UserDTO user){
        userService.updateUser(user);
    }

    @PatchMapping(path = "/password/{userId}")
    public void changePassword(@PathVariable int userId,
                               @RequestBody PasswordDTO passwordDTO) {
        userService.changePassword(userId, passwordDTO);
    }

    @PatchMapping(path = "/username/{userId}")
    public void changeUsername(@PathVariable int userId,
                               @RequestParam(value="new-username") String newUsername) {
        userService.changeUsername(userId, newUsername);
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO user){
        userService.createUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
    }

    @GetMapping(path = "/{userId}")
    @ResponseBody
    public User getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }
}
