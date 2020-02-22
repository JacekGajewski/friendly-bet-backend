package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UserAuthoritiesId;
import com.bets.friendlybet.entity.UsersAuthorities;
import com.bets.friendlybet.repository.AuthorityRepository;
import com.bets.friendlybet.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private AuthorityRepository authorityRepository;
    private UsersAuthoritiesService usersAuthoritiesService;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository, UsersAuthoritiesService usersAuthoritiesService) {
        this.authorityRepository = authorityRepository;
        this.usersAuthoritiesService = usersAuthoritiesService;
    }

    @Override
    public Authority getAuthority(UserRole role) {
        return authorityRepository.getByName(role);
    }


    @Override
    public void createAuthority(UserRole userRole, User user) {
        UserAuthoritiesId userAuthoritiesId = new UserAuthoritiesId(
                user,
                authorityRepository.getByName(userRole)
        );
        UsersAuthorities usersAuthorities = new UsersAuthorities(
               userAuthoritiesId
        );
        usersAuthoritiesService.save(usersAuthorities);
    }

    @Override
    public void deleteAuthorities(int userId) {

    }
}
