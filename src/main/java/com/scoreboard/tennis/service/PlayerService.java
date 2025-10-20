package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.GameScore;
import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.CurrentMatchStorage;
import com.scoreboard.tennis.util.Validator;

import java.util.UUID;

public class PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public UUID createNewMatch(String player1Name, String player2Name) {
        Validator.validatePlayers(player1Name, player2Name);
        if (playerRepository.findByName(player1Name).isEmpty()) {
            playerRepository.save(player1Name);
        }
        if (playerRepository.findByName(player2Name).isEmpty()) {
            playerRepository.save(player2Name);
        }

        GameScore gameScore = new GameScore(
                playerRepository.findIdByName(player1Name), 0, 0, 0,
                playerRepository.findIdByName(player2Name), 0, 0, 0
        );

        UUID matchId = UUID.randomUUID();
        CurrentMatchStorage.save(matchId, gameScore);

        return matchId;
    }
}
