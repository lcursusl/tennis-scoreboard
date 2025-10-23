package com.scoreboard.tennis.util;

import com.scoreboard.tennis.exception.GayPlayerException;
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

        if (player1.toLowerCase().contains("pidor") || player2.toLowerCase().contains("pidor") ||
                player1.toLowerCase().contains("пидор") || player2.toLowerCase().contains("пидор") ||
                player1.toLowerCase().contains("gay") || player2.toLowerCase().contains("gay") ||
                player1.toLowerCase().contains("гей") || player2.toLowerCase().contains("гей") ||
                player1.toLowerCase().contains("лох") || player2.toLowerCase().contains("лох") ||
                player1.toLowerCase().contains("slave") || player2.toLowerCase().contains("slave")) {
            throw new GayPlayerException("Геям тут не место");
        }

        if (player1.toLowerCase().contains("sex") || player2.toLowerCase().contains("sex")) {
            throw new GayPlayerException("Слишком секси для игры в теннис");
        }

        if (player1.toLowerCase().contains("drop") || player2.toLowerCase().contains("drop")) {
            throw new GayPlayerException("Себя DROPни");
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

    public static int validatePage(String page) {
        if (page == null) {
            return 1;
        }
        int pageNumber = Integer.parseInt(page);
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        return pageNumber;
    }
}
