package com.scoreboard.tennis.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PlayerRepository {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void save(String player1) {

    }
}
