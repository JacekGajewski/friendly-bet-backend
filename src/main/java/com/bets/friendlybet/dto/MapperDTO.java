package com.bets.friendlybet.dto;

import com.bets.friendlybet.entity.Bet;

import java.util.List;

public interface MapperDTO {

    Bet betDtoToBetEntity(BetDTO betDTO);

    BetDTO betEntityToBetDto(Bet betEntity);

    List<BetDTO> betEntityListToBetDtoList(List<Bet> entityBets);
}
