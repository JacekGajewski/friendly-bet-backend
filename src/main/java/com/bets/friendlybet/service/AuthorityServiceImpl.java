package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UserAuthoritiesId;
import com.bets.friendlybet.entity.UsersAuthorities;
import com.bets.friendlybet.repository.AuthorityRepository;
import com.bets.friendlybet.security.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bets.friendlybet.security.UserRole.ADMIN;
import static com.bets.friendlybet.security.UserRole.STUDENT;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;
    private final UsersAuthoritiesService usersAuthoritiesService;

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

    public void initAuthorities() {
        if (authorityRepository.getByName(STUDENT) != null){
            return;
        }
        authorityRepository.save(new Authority(STUDENT));
        authorityRepository.save(new Authority(ADMIN));
    }
}
