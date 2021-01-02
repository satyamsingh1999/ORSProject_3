package in.co.rays.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class HibDataSource.
 * @author Iterator
 * @version 1.0
 */
public class HibDataSource {

    /** The session factory. */
    private static SessionFactory sessionFactory = null;

    /**
     * Get the instance of Session Factory.
     *
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new  Configuration().configure().buildSessionFactory(); /*Configuration().configure().buildSessionFactory();*/
        }
        return sessionFactory;
    }

    /**
     * Get Session by SessionFactory.
     *
     * @return session
     */
    public static Session getSession() {
        Session session = getSessionFactory().openSession();
        return session;
    }

    /**
     * Get Session by SessionFactory.
     *
     * @param session the session
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

}
