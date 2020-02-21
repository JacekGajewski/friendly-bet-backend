package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.BetRepository;
import com.bets.friendlybet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetServiceImpl implements BetService {

    private BetRepository betRepository;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public BetServiceImpl(BetRepository betRepository, UserRepository userRepository, UserService userService) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<BetDTO> getAllBets(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        return betEntityListToBetDtoList(betRepository.getAllByBetCreatorOrBetRival(user, user));
    }

    @Override
    public BetDTO getBet(int id) {
        return betEntityToBetDto(betRepository.getOne(id));
    }

    @Override
    public List<BetDTO> getBetsByStatus(int userId, String status) {
        User user = userRepository.findById(userId).orElse(null);
        return betEntityListToBetDtoList(
                betRepository.findByStatusAndBetCreatorOrStatusAndBetRival(status, user, status, user));
    }

    @Override
    public BetDTO updateBet(BetDTO bet) {
        return betEntityToBetDto(betRepository.save(betDtoToBetEntity(bet)));
    }

    @Override
    public BetDTO saveBet(BetDTO newBet) {
        Bet s = betDtoToBetEntity(newBet);
        Bet save = betRepository.save(s);
        return betEntityToBetDto(save);
    }

    @Override
    public void deleteBet(int id) {
        betRepository.deleteById(id);
    }

    private Bet betDtoToBetEntity(BetDTO betDTO) {
        Bet bet = new Bet(
                betDTO.getBetId(),
                betDTO.getTitle(),
                betDTO.getContent(),
                betDTO.getValue(),
                betDTO.getStatus(),
                userService.getUser(betDTO.getCreatorId()),
                userService.getUser(betDTO.getRivalName()));
        return bet;
    }

    private BetDTO betEntityToBetDto(Bet betEntity) {
        BetDTO betDTO = new BetDTO(
                betEntity.getBet_id(),
                betEntity.getTitle(),
                betEntity.getContent(),
                betEntity.getValue(),
                betEntity.getStatus(),
                0,
                ""
        );

        if (betEntity.getBetCreator() != null) {
            betDTO.setCreatorId(betEntity.getBetCreator().getId());
        }
        if (betEntity.getBetRival() != null) {
            betDTO.setRivalName(betEntity.getBetRival().getUsername());
        }
        return betDTO;
    }

    private List<BetDTO> betEntityListToBetDtoList(List<Bet> entityBets) {
        return entityBets
                .stream()
                .map(this::betEntityToBetDto)
                .collect(Collectors.toList());
    }
}
