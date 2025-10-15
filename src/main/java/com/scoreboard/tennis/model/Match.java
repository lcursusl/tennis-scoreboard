package com.scoreboard.tennis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playerOne", nullable = false)
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "playerTwo", nullable = false)
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;
}
