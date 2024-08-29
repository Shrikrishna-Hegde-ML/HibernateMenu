package com.example.hibernateP;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil  {
    public static final SessionFactory sessionFactory = buildSession();
    
    public static SessionFactory buildSession(){
        SessionFactory sessionFactory1 = null;
        sessionFactory1 = new Configuration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
        return sessionFactory1;
    }
    

    public static SessionFactory getSessionFactory(){
        System.out.println(sessionFactory);
        return sessionFactory;
    }
}