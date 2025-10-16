package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.GameScore;
import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.CurrentMatchStorage;
import com.scoreboard.tennis.util.Validator;

import java.util.UUID;

public class PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public UUID createNewMatch(String player1, String player2) {
        Validator.validatePlayers(player1, player2);
        if (playerRepository.findByName(player1).isEmpty()) {
            playerRepository.save(player1);
        }
        if (playerRepository.findByName(player2).isEmpty()) {
            playerRepository.save(player2);
        }

        GameScore gameScore = new GameScore(
                playerRepository.findId(player1), 0, 0, 0,
                playerRepository.findId(player2), 0, 0, 0
        );

        UUID matchId = UUID.randomUUID();
        CurrentMatchStorage.save(matchId, gameScore);

        return matchId;
    }
}
