package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public int getUserId(String username) {
        return userRepository.findUserByUsername(username).getId();
    }
}
