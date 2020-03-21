package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.Bet;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.entity.UserBets;

public interface UserBetsService {

    UserBets saveUserBets(User user, Bet bet);

}
