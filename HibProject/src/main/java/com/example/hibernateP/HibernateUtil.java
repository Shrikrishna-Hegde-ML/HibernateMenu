package com.example.hibernateP;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil  {
    public static final SessionFactory sessionFactory = buildSession();
    
    public static SessionFactory buildSession(){
        SessionFactory sessionFactory1 = null;
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Product.class);
        sessionFactory1 = config.buildSessionFactory();
        return sessionFactory1;
    }
    

    public static SessionFactory getSessionFactory(){
        // System.out.println(sessionFactory);
        return sessionFactory;
    }
}