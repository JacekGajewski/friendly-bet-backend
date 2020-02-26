package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;

import java.util.List;

public interface BetService {

    List<BetDTO> getAllBets(int userId);

    BetDTO getBet(int id);

    List<BetDTO> getBetsByStatus(int userId, String status);

    BetDTO updateBet(BetDTO bet);

    BetDTO saveBet(BetDTO newBet);

    void deleteBet(int userId, int betId);

    void deleteUserFromBet(int userId, int betId);

    void deleteUserFromBets(int userId);

    void deleteBetsIfItIsNotAttached();
}
