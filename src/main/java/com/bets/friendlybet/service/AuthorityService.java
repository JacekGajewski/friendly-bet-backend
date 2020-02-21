package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.security.UserRole;

public interface AuthorityService {

    Authority getAuthority(UserRole role);

    void createAuthority(UserRole userRole, User user);

    void deleteAuthorities(int userId);

}
