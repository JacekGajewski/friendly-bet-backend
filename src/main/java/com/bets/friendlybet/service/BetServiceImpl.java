package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.dto.MapperDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.BetNotFoundException;
import com.bets.friendlybet.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final MapperDTO mapperDTO;

    @Override
    public List<BetDTO> getAllBets(int userId) {
        List<Bet> allUserBets = betRepository.getAllByBetCreatorOrBetRival(userId, userId);
        return mapperDTO.betEntityListToBetDtoList(allUserBets);
    }

    @Override
    public BetDTO getBet(int id) {
        return mapperDTO.betEntityToBetDto(
                betRepository.findById(id).orElseThrow(() ->
                        new BetNotFoundException("Bet: " + id + " not found")));
    }

    @Override
    public List<BetDTO> getBetsByStatus(int userId, String status) {
        return mapperDTO.betEntityListToBetDtoList(
                betRepository.findUserBetsWithStatus(status, userId, userId));
    }

    @Override
    public BetDTO updateBet(BetDTO bet) {
        return mapperDTO.betEntityToBetDto(betRepository.save(mapperDTO.betDtoToBetEntity(bet)));
    }

    @Override
    public BetDTO saveBet(BetDTO newBet) {
        Bet s = mapperDTO.betDtoToBetEntity(newBet);
        Bet save = betRepository.save(s);
        return mapperDTO.betEntityToBetDto(save);
    }

    @Override
    public void deleteBet(int userId, int betId) {
        deleteUserFromBet(userId, betId);
        betRepository.deleteBetIfItIsNotAttached(betId);
    }

    @Override
    public void deleteUserFromBet(int userId, int betId) {
        betRepository.deleteUserFromCreatorForBet(userId, betId);
        betRepository.deleteUserFromRivalForBet(userId, betId);
        deleteBetsIfItIsNotAttached();
    }

    @Override
    public void deleteUserFromBets(int userId) {
//        TODO: Circular dependency
        betRepository.deleteUserFromCreator(userId);
        betRepository.deleteUserFromRival(userId);
        deleteBetsIfItIsNotAttached();
    }

    @Override
    public void deleteBetsIfItIsNotAttached() {
        betRepository.deleteBetsIfItIsNotAttached();
    }
}
