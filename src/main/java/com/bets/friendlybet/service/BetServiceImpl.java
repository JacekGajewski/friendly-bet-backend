package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.dto.MapperDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.BetNotFoundException;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.repository.BetRepository;
import com.bets.friendlybet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final MapperDTO mapperDTO;

    @Override
    public List<BetDTO> getAllBets(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User: " + userId + " not found"));
        return mapperDTO.betEntityListToBetDtoList(betRepository.getAllByBetCreatorOrBetRival(user, user));
    }

    @Override
    public BetDTO getBet(int id) {
        return mapperDTO.betEntityToBetDto(
                betRepository.findById(id).orElseThrow(() ->
                        new BetNotFoundException("Bet: " + id + " not found")));
    }

    @Override
    public List<BetDTO> getBetsByStatus(int userId, String status) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User: " + userId + " not found"));
        return mapperDTO.betEntityListToBetDtoList(
                betRepository.findByStatusAndBetCreatorOrStatusAndBetRival(status, user, status, user));
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
        betRepository.deleteUserFromCreator(userId);
        betRepository.deleteUserFromRival(userId);
        deleteBetsIfItIsNotAttached();
    }

    @Override
    public void deleteBetsIfItIsNotAttached() {
        betRepository.deleteBetsIfItIsNotAttached();
    }
}
