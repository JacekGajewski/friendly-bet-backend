package com.bets.friendlybet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private int userId;

    private String username;

    private String password;

    public UserDTO(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
