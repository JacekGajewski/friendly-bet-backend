package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {

}
