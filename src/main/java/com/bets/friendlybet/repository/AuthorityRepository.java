package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority getByName(UserRole role);

    void deleteAllByUser(User user);
}
