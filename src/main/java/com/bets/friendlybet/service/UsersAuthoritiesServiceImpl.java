package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.UsersAuthorities;
import com.bets.friendlybet.repository.UsersAuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersAuthoritiesServiceImpl implements UsersAuthoritiesService {

    private final UsersAuthoritiesRepository usersAuthoritiesRepository;

    @Override
    public UsersAuthorities save(UsersAuthorities usersAuthorities) {
        return usersAuthoritiesRepository.save(usersAuthorities);
    }

    @Override
    public void deleteUserAuthorities(int userId) {
        usersAuthoritiesRepository.deleteUsersAuthoritiesByIdUserIsContaining(userId);
    }
}
