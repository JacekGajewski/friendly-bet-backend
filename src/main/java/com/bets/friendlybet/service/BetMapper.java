package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BetMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Bet betDtoToBetEntity(BetDTO betDTO) {
        return new Bet(
                betDTO.getBetId(),
                betDTO.getTitle(),
                betDTO.getContent(),
                betDTO.getValue(),
                betDTO.getStatus(),
                userRepository.findById(betDTO.getCreatorId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
    }

    public BetDTO betEntityToBetDto(Bet betEntity) {
        List<User> listOfParticipants = null;
        if (betEntity.getParticipants() != null) {
            listOfParticipants = betEntity.getParticipants()
                    .stream()
                    .map(p -> p.getId().getUser())
                    .collect(Collectors.toList());
        }


        BetDTO betDTO = new BetDTO(
                betEntity.getId(),
                betEntity.getTitle(),
                betEntity.getContent(),
                betEntity.getValue(),
                betEntity.getStatus(),
                0,
                userMapper.usersListToUsersResponseDtoList(listOfParticipants)
        );

        if (betEntity.getBetCreator() != null) {
            betDTO.setCreatorId(betEntity.getBetCreator().getId());
        }

        return betDTO;
    }

    public List<BetDTO> betEntityListToBetDtoList(List<Bet> entityBets) {
        return entityBets
                .stream()
                .map(this::betEntityToBetDto)
                .collect(Collectors.toList());
    }

    public Set<BetDTO> betEntityListToBetDtoList(Set<Bet> entityBets) {
        return entityBets
                .stream()
                .map(this::betEntityToBetDto)
                .collect(Collectors.toSet());
    }
}
