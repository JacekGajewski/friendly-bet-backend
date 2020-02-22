package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.UsersAuthorities;
import com.bets.friendlybet.entity.UserAuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersAuthoritiesRepository extends JpaRepository<UsersAuthorities, UserAuthoritiesId> {

}
