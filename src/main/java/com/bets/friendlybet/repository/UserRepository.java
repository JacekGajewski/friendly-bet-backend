package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
