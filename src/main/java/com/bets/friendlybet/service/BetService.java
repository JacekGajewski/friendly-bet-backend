package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;

import java.util.List;

public interface BetService {

    BetDTO getBet(int id);

    List<BetDTO> getAllBets(int userId);

    BetDTO updateBet(BetDTO bet);

    BetDTO saveBet(BetDTO newBet);

    void deleteBet(int betId);
}
