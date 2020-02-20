package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {

    List<Bet> getAllByBetCreatorOrBetRival(User creator, User rival);

    List<Bet> getAllByBetCreatorOrBetRivalAndStatus(User creator, User rival, String status);

    List<Bet> getByStatusAndBetCreatorOrBetRival(String status, User creator, User rival);

    List<Bet> findByStatusAndBetCreatorOrStatusAndBetRival(String status, User creator, String theStatus, User rival);

}
