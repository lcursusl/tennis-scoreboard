package com.scoreboard.tennis.service;

import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.Validator;

public class PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();


    public String createNewMatch(String player1, String player2) {
        Validator.validatePlayers(player1, player2);

        playerRepository.save(player1);
        playerRepository.save(player2);

        return "uuid";
    }
}
