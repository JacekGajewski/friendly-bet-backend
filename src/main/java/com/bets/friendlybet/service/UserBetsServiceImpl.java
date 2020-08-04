package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UserBets;
import com.bets.friendlybet.entity.UserBetsId;
import com.bets.friendlybet.repository.UserBetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBetsServiceImpl implements UserBetsService {

    private final UserBetsRepository userBetsRepository;

    @Override
    public UserBets saveUserBets(User user, Bet bet) {
        UserBetsId userBetsId = new UserBetsId(user, bet);
        UserBets userBets = new UserBets(userBetsId);
        return userBetsRepository.save(userBets);
    }
}
