package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.model.Player;
import com.scoreboard.tennis.repository.MatchRepository;
import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.CurrentMatchStorage;

import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    public void persist(MatchDto match, Long winnerId) {
        CurrentMatchStorage.delete(UUID.fromString(match.getId()));
        Player winner = playerRepository.findById(winnerId);
        matchRepository.save(match.getPlayer1(), match.getPlayer2(), winner);
    }
}
