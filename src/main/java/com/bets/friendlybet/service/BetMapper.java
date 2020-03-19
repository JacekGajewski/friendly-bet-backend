package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.repository.UserRepository;
import com.bets.friendlybet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BetMapper {

    private final UserRepository userRepository;

    public Bet betDtoToBetEntity(BetDTO betDTO) {
        return new Bet(
                betDTO.getBetId(),
                betDTO.getTitle(),
                betDTO.getContent(),
                betDTO.getValue(),
                betDTO.getStatus(),
                userRepository.findById(betDTO.getCreatorId())
                        .orElseThrow(() -> new UserNotFoundException("User not found")),
                userRepository.findUserByUsername(betDTO.getRivalName())
                        .orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    public BetDTO betEntityToBetDto(Bet betEntity) {
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

    public List<BetDTO> betEntityListToBetDtoList(List<Bet> entityBets) {
        return entityBets
                .stream()
                .map(this::betEntityToBetDto)
                .collect(Collectors.toList());
    }
}
