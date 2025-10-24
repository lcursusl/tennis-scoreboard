package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;
    private Player player1;
    private Player player2;

    @BeforeEach
    void init() {
        matchScoreCalculationService = new MatchScoreCalculationService();
        player1 = new Player(1L, "player1");
        player2 = new Player(2L, "player2");
    }

    @Test
    void player1WinsPointAt40_40AndGameDoesNotEnd() {
        MatchDto match = new MatchDto(
                "test-uuid",
                player1, 1, 5, "40",
                player2, 0, 0, "40"
        );
        MatchDto updatedMatch = matchScoreCalculationService.updateMatchScore(match, player1.getId());

        assertFalse(matchScoreCalculationService.matchIsOver(updatedMatch), "Гейм не должен завершиться");
    }

    @Test
    void player1WinsPointAt40_0AndGameEnd() {
        MatchDto match = new MatchDto(
                "test-uuid",
                player1, 1, 5, "40",
                player2, 0, 0, "0"
        );
        MatchDto updatedMatch = matchScoreCalculationService.updateMatchScore(match, player1.getId());

        assertTrue(matchScoreCalculationService.matchIsOver(updatedMatch), "Гейм должен завершиться");
    }

    @Test
    void player1WinsPointAt6_6AndTieBreakStarted() {
        MatchDto match = new MatchDto(
                "test-uuid",
                player1, 0, 6, "0",
                player2, 0, 6, "0"
        );
        MatchDto updatedMatch = matchScoreCalculationService.updateMatchScore(match, player1.getId());

        assertEquals("1", updatedMatch.getPlayer1Points(), "Должен начаться Тай-Брейк");
    }
}
