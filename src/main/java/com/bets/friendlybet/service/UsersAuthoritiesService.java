package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UsersAuthorities;

public interface UsersAuthoritiesService {

    UsersAuthorities save(UsersAuthorities usersAuthorities);

    void deleteUserAuthorities(int userId);

}
