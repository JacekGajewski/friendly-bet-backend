package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.repository.BetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    private BetRepository betRepository;

    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    @Override
    public Bet getBet(int id) {
        return betRepository.getOne(id);
    }

    @Override
    public Bet saveBet(Bet newBet) {
       return betRepository.save(newBet);
    }

    @Override
    public void deleteBet(int id) {betRepository.deleteById(id);
    }
}
