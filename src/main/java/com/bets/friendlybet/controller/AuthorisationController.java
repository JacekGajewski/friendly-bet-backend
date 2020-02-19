package com.bets.friendlybet.controller;

import com.bets.friendlybet.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthorisationController {

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return
                user.getUsername().equals("user") && user.getPassword().equals("password");
    }
}
