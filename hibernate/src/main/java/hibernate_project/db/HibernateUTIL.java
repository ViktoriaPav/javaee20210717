package hibernate_progect.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUTIL {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed");
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public  static void shutdown() {
        getSessionFactory().close();
    }

}
