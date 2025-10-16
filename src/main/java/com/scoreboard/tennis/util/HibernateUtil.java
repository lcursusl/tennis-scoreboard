package com.scoreboard.tennis.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try{
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
