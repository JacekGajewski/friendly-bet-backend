package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Integer> {
}
