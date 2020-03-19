package com.bets.friendlybet.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private int userId;

    private String username;

    public UserResponseDTO(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
