package com.scoreboard.tennis.dto;

import com.scoreboard.tennis.model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchDto {
    private String id;
    private Player player1;
    private int player1Sets;
    private int player1Games;
    private String player1Points;
    private Player player2;
    private int player2Sets;
    private int player2Games;
    private String player2Points;
}
