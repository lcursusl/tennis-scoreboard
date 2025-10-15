package com.scoreboard.tennis.util;

import com.scoreboard.tennis.exception.InvalidPlayerException;
import com.scoreboard.tennis.exception.SamePlayersException;

public class Validator {
    public static void validatePlayers(String player1, String player2) {
        if (player1 == null || player1.trim().isEmpty() ||
                player2 == null || player2.trim().isEmpty()) {
            throw new InvalidPlayerException("Player names cannot be null or empty");
        }

        if (player1.length() > 48 || player2.length() > 48) {
            throw new InvalidPlayerException("Player name too long (max 48 characters)");
        }

        if (player1.equalsIgnoreCase(player2.trim())) {
            throw new SamePlayersException("Players must have different names");
        }
    }
}
