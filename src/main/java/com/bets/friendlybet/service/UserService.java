package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.User;

public interface UserService {

    User getUser(int userId);

    User getUser(String username);

    int getUserId(String username);

}
