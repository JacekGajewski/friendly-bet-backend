package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.dto.MapperDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.BetRepository;
import com.bets.friendlybet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private UserRepository userRepository;
    private MapperDTO mapperDTO;

    @Autowired
    public BetServiceImpl(BetRepository betRepository, UserRepository userRepository, MapperDTO mapperDTO) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.mapperDTO = mapperDTO;
    }

    @Override
    public List<BetDTO> getAllBets(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        return mapperDTO.betEntityListToBetDtoList(betRepository.getAllByBetCreatorOrBetRival(user, user));
    }

    @Override
    public BetDTO getBet(int id) {
        return mapperDTO.betEntityToBetDto(betRepository.getOne(id));
    }

    @Override
    @Transactional
    public List<BetDTO> getBetsByStatus(int userId, String status) {
        User user = userRepository.findById(userId).orElse(null);
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
    public void deleteBet(int id) {
        betRepository.deleteById(id);
    }
}
