package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.PasswordDTO;
import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(int userId);

    User getUser(String username);

    void updateUser(UserDTO user);

    void changePassword(int userId, PasswordDTO passwordDTO);

    void changeUsername(int userId, String newUsername);

    void createUser(UserDTO user);

    void deleteUser(int userId);
}
