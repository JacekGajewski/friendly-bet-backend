package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.UserBets;
import com.bets.friendlybet.entity.UserBetsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBetsRepository extends JpaRepository<UserBets, UserBetsId> {
}
