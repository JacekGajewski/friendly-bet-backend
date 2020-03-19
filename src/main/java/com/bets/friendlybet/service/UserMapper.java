package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.dto.UserResponseDTO;
import com.bets.friendlybet.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {


    public List<UserResponseDTO> usersListToUsersResponseDtoList(List<User> users) {
        return null;
    }

    public UserResponseDTO userToUserResponseDto(User user) {
        return null;
    }

    public User userDtoToUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword()
        );
    }
}
