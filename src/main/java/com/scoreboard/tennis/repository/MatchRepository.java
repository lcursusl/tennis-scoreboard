package com.scoreboard.tennis.repository;

import com.scoreboard.tennis.model.Match;
import com.scoreboard.tennis.model.Player;
import com.scoreboard.tennis.util.HibernateUtil;
import org.hibernate.Session;

public class MatchRepository {
    public void save(Player playerOne, Player playerTwo, Player winner) {
        Match match = new Match();
        match.setPlayerOne(playerOne);
        match.setPlayerTwo(playerTwo);
        match.setWinner(winner);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }
}
