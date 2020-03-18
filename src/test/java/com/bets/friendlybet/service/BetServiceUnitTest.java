package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.BetDTO;
import com.bets.friendlybet.dto.MapperDTO;
import com.bets.friendlybet.dto.MapperDTOImpl;
import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.BetRepository;
import com.bets.friendlybet.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BetServiceUnitTest {

    @Mock
    MapperDTOImpl mapperDTO;

    @Mock
    BetRepository betRepository;

    @InjectMocks
    private BetServiceImpl betService;

    private BetDTO betDTO;
    private List<BetDTO> betsDTO;

    @Before
    public void init() {
        User creator = new User(1, "johnny", "passwordJohnny");
        User rival = new User(2, "jack", "passwordJack");

        betDTO = new BetDTO(1, "SportsBet", "Manchester United is going to loose to Arsenal",
                "One beer", "pending", 1, "jack");
        Bet bet = new Bet("SportsBet", "Manchester United is going to loose to Arsenal",
                "One beer", "pending", creator, rival);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet);

        betsDTO = new ArrayList<>();
        betsDTO.add(betDTO);

        when(mapperDTO.betDtoToBetEntity(betDTO)).thenReturn(bet);
        when(mapperDTO.betEntityListToBetDtoList(bets)).thenReturn(betsDTO);
        when(mapperDTO.betEntityToBetDto(bet)).thenReturn(betDTO);
        when(betRepository.save(bet)).thenReturn(bet);
        when(betRepository.getAllByBetCreatorOrBetRival(1, 1)).thenReturn(bets);
        when(betRepository.findUserBetsWithStatus("pendind", 1, 1)).thenReturn(bets);
    }

    @Test
    public void sth() {

        assertThat(betService.saveBet(betDTO)).isEqualTo(betDTO);
        assertThat(betService.getAllBets(1)).isEqualTo(betsDTO);
    }
}
