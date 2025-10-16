package com.scoreboard.tennis.repository;

import com.scoreboard.tennis.model.Player;
import com.scoreboard.tennis.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository {
    public void save(String name) {
        Player player = new Player();
        player.setName(name);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }

    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Player as p where p.name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        }
    }

    public Long findId(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select p.id from Player as p where p.name = :name", Long.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }
}
