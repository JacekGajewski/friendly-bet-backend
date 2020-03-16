package com.bets.friendlybet.exception;

public class BadPasswordException extends RuntimeException{

    public BadPasswordException(String message) {
        super(message);
    }
}
