package com.scoreboard.tennis.dto;

public record GameScore(
        Long id1,
        int sets1,
        int games1,
        String points1,
        Long id2,
        int sets2,
        int games2,
        String points2
) {
}