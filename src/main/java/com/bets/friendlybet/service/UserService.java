package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.dto.PasswordDTO;
import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.dto.UserResponseDTO;
import com.bets.friendlybet.entity.User;

import java.util.List;

public interface UserService {

    User getUserEntity(int userId);

    User getUserEntity(String username);

    UserResponseDTO getUser(int userId);

    UserResponseDTO getUser(String username);

    List<UserResponseDTO> getAllUsers();

    List<BetDTO> getAllUserBets(int userId);

    List<BetDTO> getBetsByStatus(int userId, String betStatus);

    void updateUser(UserDTO user);

    void changePassword(int userId, PasswordDTO passwordDTO);

    void changeUsername(int userId, String newUsername);

    void createUser(UserDTO user);

    void deleteUser(int userId);
}
