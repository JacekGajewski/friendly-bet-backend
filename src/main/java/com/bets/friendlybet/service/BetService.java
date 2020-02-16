package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Bet;

import java.util.List;

public interface BetService {

    List<Bet> getAllBets();

    Bet getBet(int id);

    Bet saveBet(Bet newBet);

    void deleteBet(int id);
}
