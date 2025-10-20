package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.GameScore;
import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.repository.MatchRepository;
import com.scoreboard.tennis.repository.PlayerRepository;
import com.scoreboard.tennis.util.CurrentMatchStorage;
import com.scoreboard.tennis.util.Validator;

import java.util.UUID;

public class MatchService {
    private final MatchRepository matchRepository = new MatchRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    public MatchDto getScore(String uuid) {
        Validator.validateUuid(uuid);
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

    /*public UUID updateMatchScore(String uuid) {
        Validator.validateUuid(uuid);
        GameScore gameScore = CurrentMatchStorage.get(UUID.fromString(uuid));


    }*/
}
