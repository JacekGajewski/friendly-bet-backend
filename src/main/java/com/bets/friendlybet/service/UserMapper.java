package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.dto.UserResponseDTO;
import com.bets.friendlybet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> usersListToUsersResponseDtoList(List<User> users) {
        if (users == null) return null;
        return users
                .stream()
                .map(this::userToUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO userToUserResponseDto(User user) {
        if (user == null) return null;
        return new UserResponseDTO(
                user.getId(),
                user.getUsername()
        );
    }

    public User userDtoToUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword())
        );
    }
}
