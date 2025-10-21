package com.scoreboard.tennis.dto;

public record ScoreResult(int winnerSets, int loserSets, int winnerGames, int loserGames, String winnerPoints,
                          String loserPoints) {
}
