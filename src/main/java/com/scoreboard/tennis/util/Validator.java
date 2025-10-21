package com.scoreboard.tennis.util;

import com.scoreboard.tennis.exception.InvalidIdException;
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

        if (player1.equalsIgnoreCase(player2)) {
            throw new SamePlayersException("Players must have different names");
        }
    }

    public static void validatePlayerId(Long id) {
        if (id == null || id == 0L) {
            throw new InvalidIdException("Couldn't find a player");
        }
    }

    public static void validateUuid(String uuid) {
        if (uuid == null || uuid.trim().isEmpty()) {
            throw new InvalidIdException("Couldn't find a match");
        }
    }
}
