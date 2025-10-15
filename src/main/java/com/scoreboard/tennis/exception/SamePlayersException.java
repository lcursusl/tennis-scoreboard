package com.scoreboard.tennis.exception;

public class SamePlayersException extends RuntimeException {
    public SamePlayersException(String message) {
        super(message);
    }
}
