package com.bets.friendlybet.exception;

public class NotUniqueUsernameException extends RuntimeException{

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}
