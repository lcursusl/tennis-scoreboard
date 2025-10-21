package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.GameScore;
import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.CurrentMatchStorage;

import java.util.UUID;

public class OngoingMatchesService {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public MatchDto getMatch(String uuid) {
        GameScore gameScore = CurrentMatchStorage.get(UUID.fromString(uuid));
        return new MatchDto(
                uuid,
                playerRepository.findById(gameScore.id1()),
                gameScore.sets1(),
                gameScore.games1(),
                gameScore.points1(),
                playerRepository.findById(gameScore.id2()),
                gameScore.sets2(),
                gameScore.games2(),
                gameScore.points2()
        );
    }

    public void saveMatch(String uuid, MatchDto match) {
        GameScore gameScore = new GameScore(
                match.getPlayer1().getId(),
                match.getPlayer1Sets(),
                match.getPlayer1Games(),
                match.getPlayer1Points(),
                match.getPlayer2().getId(),
                match.getPlayer2Sets(),
                match.getPlayer2Games(),
                match.getPlayer2Points()
        );
        CurrentMatchStorage.save(UUID.fromString(uuid), gameScore);
    }
}
