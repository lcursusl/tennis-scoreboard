package com.scoreboard.tennis.repository;

import com.scoreboard.tennis.model.Match;
import com.scoreboard.tennis.model.Player;
import com.scoreboard.tennis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository {
    public void save(Player playerOne, Player playerTwo, Player winner) {
        Match match = new Match();
        match.setPlayer1(playerOne);
        match.setPlayer2(playerTwo);
        match.setWinner(winner);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public List<Match> findAll(int currentPage) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class)
                    .setFirstResult((currentPage - 1) * 5)
                    .setMaxResults(5)
                    .getResultList();
        }
    }

    public List<Match> findByName(int currentPage, String filterByPlayerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match m where m.player1.name like :player1 or m.player2.name like :player2", Match.class)
                    .setParameter("player1", "%" + filterByPlayerName + "%")
                    .setParameter("player2", "%" + filterByPlayerName + "%")
                    .setFirstResult((currentPage - 1) * 5)
                    .setMaxResults(5)
                    .getResultList();
        }
    }

    public Long findTotalPages() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long countMatches = session.createQuery("select count(*) from Match", Long.class).uniqueResult();
            return (countMatches + 5 - 1) / 5;
        }
    }

    public Long findTotalPages(String filterByPlayerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long countMatches = session.createQuery("select count(*) from Match m where m.player1.name like :player1 or m.player2.name like :player2", Long.class)
                    .setParameter("player1", "%" + filterByPlayerName + "%")
                    .setParameter("player2", "%" + filterByPlayerName + "%")
                    .uniqueResult();
            return (countMatches + 5 - 1) / 5;
        }
    }
}
