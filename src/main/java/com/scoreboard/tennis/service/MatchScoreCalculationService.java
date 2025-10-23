package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.dto.ScoreResult;
import com.scoreboard.tennis.dto.SetsResult;

public class MatchScoreCalculationService {
    public MatchDto updateMatchScore(MatchDto match, Long winnerId) {
        int sets1 = match.getPlayer1Sets();
        int sets2 = match.getPlayer2Sets();
        int games1 = match.getPlayer1Games();
        int games2 = match.getPlayer2Games();
        String points1 = match.getPlayer1Points();
        String points2 = match.getPlayer2Points();

        if (match.getPlayer1().getId().equals(winnerId)) {
            ScoreResult scoreResult = calculateScore(sets1, sets2, games1, games2, points1, points2);
            return new MatchDto(
                    match.getId(),
                    match.getPlayer1(),
                    scoreResult.winnerSets(),
                    scoreResult.winnerGames(),
                    scoreResult.winnerPoints(),
                    match.getPlayer2(),
                    scoreResult.loserSets(),
                    scoreResult.loserGames(),
                    scoreResult.loserPoints()
            );
        }
        ScoreResult scoreResult = calculateScore(sets2, sets1, games2, games1, points2, points1);
        return new MatchDto(
                match.getId(),
                match.getPlayer1(),
                scoreResult.loserSets(),
                scoreResult.loserGames(),
                scoreResult.loserPoints(),
                match.getPlayer2(),
                scoreResult.winnerSets(),
                scoreResult.winnerGames(),
                scoreResult.winnerPoints()
        );
    }

    public boolean matchIsOver(MatchDto match) {
        return match.getPlayer1Sets() == 2 || match.getPlayer2Sets() == 2;
    }

    private ScoreResult calculateScore(int winnerSets, int loserSets, int winnerGames, int loserGames, String winnerPoints, String loserPoints) {
        if (winnerGames == 6 && loserGames == 6) {
            return calculateTieBreak(winnerSets, loserSets, winnerGames, loserGames, winnerPoints, loserPoints);
        }
        switch (winnerPoints) {
            case "0" -> winnerPoints = "15";
            case "15" -> winnerPoints = "30";
            case "30" -> winnerPoints = "40";
            case "40" -> {
                if (loserPoints.equals("Advantage")) {
                    loserPoints = "40";
                } else if (loserPoints.equals("40")) {
                    winnerPoints = "Advantage";
                } else {
                    winnerPoints = "0";
                    loserPoints = "0";
                    SetsResult setsResult = updateSetsAndGames(winnerSets, winnerGames, loserGames);
                    winnerSets = setsResult.winnerSets();
                    winnerGames = setsResult.winnerGames();
                    loserGames = setsResult.loserGames();
                }
            }
            case "Advantage" -> {
                winnerPoints = "0";
                loserPoints = "0";
                SetsResult setsResult = updateSetsAndGames(winnerSets, winnerGames, loserGames);
                winnerSets = setsResult.winnerSets();
                winnerGames = setsResult.winnerGames();
                loserGames = setsResult.loserGames();
            }
        }
        return new ScoreResult(winnerSets, loserSets, winnerGames, loserGames, winnerPoints, loserPoints);
    }

    private ScoreResult calculateTieBreak(int winnerSets, int loserSets, int winnerGames, int loserGames, String winnerPoints, String loserPoints) {
        if (winnerPoints.equals("100") && loserPoints.equals("100")) {
            winnerPoints = "10";
            loserPoints = "10";
        }

        int winnerPointsInt = Integer.parseInt(winnerPoints);
        int loserPointsInt = Integer.parseInt(loserPoints);

        if (winnerPointsInt - loserPointsInt == 1 && winnerPointsInt > 5) {
            winnerPoints = "0";
            loserPoints = "0";
            winnerGames = 0;
            loserGames = 0;
            winnerSets++;
        } else {
            winnerPointsInt++;
            winnerPoints = String.valueOf(winnerPointsInt);
        }
        return new ScoreResult(winnerSets, loserSets, winnerGames, loserGames, winnerPoints, loserPoints);
    }

    private SetsResult updateSetsAndGames(int winnerSets, int winnerGames, int loserGames) {
        switch (winnerGames) {
            case 5 -> {
                if (loserGames < 5) {
                    winnerGames = 0;
                    loserGames = 0;
                    winnerSets++;
                } else {
                    winnerGames = 6;
                }
            }
            case 6 -> {
                winnerGames = 0;
                loserGames = 0;
                winnerSets++;
            }
            default -> winnerGames++;
        }
        return new SetsResult(winnerSets, winnerGames, loserGames);
    }
}
