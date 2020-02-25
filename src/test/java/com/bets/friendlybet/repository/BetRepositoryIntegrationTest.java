package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BetRepository betRepository;

    private User creator;
    private User rival;

    @Before
    public void setUp() {
        creator = new User("johnny", "passwordJohnny");
        rival = new User("jack", "passwordJack");

        Bet bet = new Bet("SportsBet", "Manchester United is going to loose to Arsenal",
                "One beer", "pending", creator, rival);

        entityManager.persist(creator);
        entityManager.persist(rival);
        entityManager.persist(bet);
    }

    @Test
    public void whenGetAllByBetCreatorOrBetRival_thenReturnBetList() {

        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, rival)).hasSize(1);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(null, null)).hasSize(0);

        User user = new User("johnny", "passwordJohnny");
        entityManager.persist(user);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(user, null)).hasSize(0);

        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, null)).hasSize(1);

        User user2 = new User("ben", "passwordJohnny");
        entityManager.persist(user2);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(user2, null)).hasSize(0);
    }

    @Test
    public void whenDeleteUserFromCreator() {

        int creatorId = betRepository.getAllByBetCreatorOrBetRival(creator, creator).get(0).getBetCreator().getId();
        betRepository.deleteUserFromCreator(creatorId);

        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, null)).hasSize(0);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, creator)).hasSize(0);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, rival)).hasSize(1);
    }

    @Test
    public void whenDeleteUserFromRival() {

        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, rival)).hasSize(1);

        int rivalId = betRepository.getAllByBetCreatorOrBetRival(null, rival).get(0).getBetRival().getId();
        betRepository.deleteUserFromRival(rivalId);

        assertThat(betRepository.getAllByBetCreatorOrBetRival(null, rival)).hasSize(0);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(rival, rival)).hasSize(0);
        assertThat(betRepository.getAllByBetCreatorOrBetRival(creator, rival)).hasSize(1);
    }
}
