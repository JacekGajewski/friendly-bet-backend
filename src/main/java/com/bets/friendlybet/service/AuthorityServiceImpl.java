package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.AuthorityRepository;
import com.bets.friendlybet.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getAuthority(UserRole role) {
        return authorityRepository.getByName(role);
    }


    @Override
    public void createAuthority(UserRole userRole, User user) {
        authorityRepository.save(new Authority(userRole, user));
    }

    @Override
    public void deleteAuthorities(int userId) {

    }
}
