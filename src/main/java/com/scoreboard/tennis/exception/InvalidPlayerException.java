package com.scoreboard.tennis.exception;

public class InvalidPlayerException extends RuntimeException {
    public InvalidPlayerException(String message) {
        super(message);
    }
}
