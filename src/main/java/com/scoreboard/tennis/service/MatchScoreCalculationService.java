package com.scoreboard.tennis.service;

import com.scoreboard.tennis.dto.MatchDto;

public class MatchScoreCalculationService {
    public MatchDto updateMatchScore(MatchDto match, Long winnerId) {
        int sets1 = match.getPlayer1Sets();
        int sets2 = match.getPlayer2Sets();
        int games1 = match.getPlayer1Games();
        int games2 = match.getPlayer2Games();
        String points1 = match.getPlayer1Points();
        String points2 = match.getPlayer2Points();

        if (match.getPlayer1().getId().equals(winnerId)) {
            switch (points1) {
                case "0" -> points1 = "15";
                case "15" -> points1 = "30";
                case "30" -> points1 = "40";
                case "40" -> {
                    switch (points2) {
                        case "Advantage" -> points2 = "40";
                        case "40" -> points1 = "Advantage";
                        default -> {
                            points1 = "0";
                            points2 = "0";
                            switch (games1) {
                                case 5 -> {
                                    if (games2 < 5) {
                                        games1 = 0;
                                        games2 = 0;
                                        sets1++;
                                    } else {
                                        games1 = 6;
                                    }
                                }
                                case 6 -> {
                                    if (games2 < 6) {
                                        games1 = 0;
                                        games2 = 0;
                                        sets1++;
                                    } else {
                                        games1 = 0;
                                        games2 = 0;
                                        sets1++;
                                        //taiBreak
                                    }
                                }
                                default -> games1++;
                            }
                        }
                    }
                }
                case "Advantage" -> {
                    points1 = "0";
                    points2 = "0";
                    switch (games1) {
                        case 5 -> {
                            if (games2 < 5) {
                                games1 = 0;
                                games2 = 0;
                                sets1++;
                            } else {
                                games1 = 6;
                            }
                        }
                        case 6 -> {
                            if (games2 < 6) {
                                games1 = 0;
                                games2 = 0;
                                sets1++;
                            } else {
                                games1 = 0;
                                games2 = 0;
                                sets1++;
                                //taiBreak
                            }
                        }
                        default -> games1++;
                    }
                }
            }
        } else {
            switch (points2) {
                case "0" -> points2 = "15";
                case "15" -> points2 = "30";
                case "30" -> points2 = "40";
                case "40" -> {
                    switch (points1) {
                        case "Advantage" -> points1 = "40";
                        case "40" -> points2 = "Advantage";
                        default -> {
                            points2 = "0";
                            points1 = "0";
                            switch (games2) {
                                case 5 -> {
                                    if (games1 < 5) {
                                        games2 = 0;
                                        games1 = 0;
                                        sets2++;
                                    } else {
                                        games2 = 6;
                                    }
                                }
                                case 6 -> {
                                    if (games1 < 6) {
                                        games2 = 0;
                                        games1 = 0;
                                        sets1++;
                                    } else {
                                        games2 = 0;
                                        games1 = 0;
                                        sets2++;
                                        //taiBreak
                                    }
                                }
                                default -> games2++;
                            }
                        }
                    }
                }
                case "Advantage" -> {
                    points2 = "0";
                    points1 = "0";
                    switch (games2) {
                        case 5 -> {
                            if (games1 < 5) {
                                games2 = 0;
                                games1 = 0;
                                sets2++;
                            } else {
                                games2 = 6;
                            }
                        }
                        case 6 -> {
                            if (games1 < 6) {
                                games2 = 0;
                                games1 = 0;
                                sets1++;
                            } else {
                                games2 = 0;
                                games1 = 0;
                                sets2++;
                                //taiBreak
                            }
                        }
                        default -> games2++;
                    }
                }
            }
        }
        return new MatchDto(
                match.getId(),
                match.getPlayer1(),
                sets1,
                games1,
                points1,
                match.getPlayer2(),
                sets2,
                games2,
                points2
        );
    }

    public boolean matchIsOver(MatchDto match) {
        return match.getPlayer1Sets() == 2 || match.getPlayer2Sets() == 2;
    }
}
