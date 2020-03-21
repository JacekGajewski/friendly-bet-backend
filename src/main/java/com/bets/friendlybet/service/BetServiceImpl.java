package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.exception.BetNotFoundException;
import com.bets.friendlybet.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final BetMapper betMapper;

    @Override
    public List<BetDTO> getAllBets(int userId) {
        List<Bet> allBets = betRepository.findAll();
        return betMapper.betEntityListToBetDtoList(allBets);
    }

    @Override
    public BetDTO getBet(int id) {
        return betMapper.betEntityToBetDto(
                betRepository.findById(id).orElseThrow(() ->
                        new BetNotFoundException("Bet: " + id + " not found")));
    }

    @Override
    public BetDTO updateBet(BetDTO betDTO) {
        Bet betEntity = betMapper.betDtoToBetEntity(betDTO);
        Bet bet = betRepository.save(betEntity);
        return betMapper.betEntityToBetDto(bet);
    }

    @Override
    public BetDTO saveBet(BetDTO newBet) {
        Bet s = betMapper.betDtoToBetEntity(newBet);
        Bet save = betRepository.save(s);
        return betMapper.betEntityToBetDto(save);
    }

    @Override
    public void deleteBet(int betId) {
        betRepository.deleteById(betId);
    }
}
