package com.flamexander.hibernate.product_homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao {
    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    public User findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }
    public List<User> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = (List<User>)session.createQuery("from User", List.class);
            session.getTransaction().commit();
            return users;
        }
    }

    // CRUD:

    public  void createUser(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User Oliver = new User("Oliver");
            System.out.println(Oliver);
            session.save(Oliver);
            System.out.println(Oliver);
            session.getTransaction().commit();
            System.out.println(Oliver);
        }
    }

    public void readUser(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.find(User.class, 1L);
            System.out.println(user);
            session.getTransaction().commit();
        }
    }


    public void updateUser(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User product = session.get(User.class, 1L);
            product.setName("Clyde");
            session.getTransaction().commit();
        }
    }


    public void deleteUser(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.find(User.class, 1L);
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
