package com.bets.friendlybet.exception;

public class BetNotFoundException extends RuntimeException{

    public BetNotFoundException(String message) {
        super(message);
    }
}
