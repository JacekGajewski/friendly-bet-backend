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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BetServiceUnitTest {

    @Mock
    UserRepository userRepository;

    @Mock
    MapperDTOImpl mapperDTO;

    @Mock
    BetRepository betRepository;

    @InjectMocks
    private BetServiceImpl betService = new BetServiceImpl(betRepository, userRepository, mapperDTO);

    private BetDTO betDTO;
    private  Bet bet;
    private User creator;
    private User rival;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        creator = new User("johnny", "passwordJohnny");
        rival = new User("jack", "passwordJack");

        betDTO = new BetDTO(1, "SportsBet", "Manchester United is going to loose to Arsenal",
                "One beer", "pending", 1, "jack");
        bet = new Bet("SportsBet", "Manchester United is going to loose to Arsenal",
                "One beer", "pending", creator, rival);

        when(mapperDTO.betDtoToBetEntity(betDTO)).thenReturn(bet);
        when(mapperDTO.betEntityToBetDto(bet)).thenReturn(betDTO);
        when(betRepository.save(bet)).thenReturn(bet);
    }

    @Test
    public void sth() {

        assertThat(betService.saveBet(betDTO)).isEqualTo(betDTO);

    }

}
