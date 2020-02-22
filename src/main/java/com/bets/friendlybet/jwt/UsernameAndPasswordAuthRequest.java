package com.bets.friendlybet.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}
