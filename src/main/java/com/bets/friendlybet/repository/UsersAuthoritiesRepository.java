package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UsersAuthorities;
import com.bets.friendlybet.entity.UserAuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsersAuthoritiesRepository extends JpaRepository<UsersAuthorities, UserAuthoritiesId> {

}
