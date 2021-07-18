package com.flamexander.hibernate.product_homework;

import com.flamexander.hibernate.PrepareDataApp;
import com.flamexander.hibernate.many_to_many.Book;
import com.flamexander.hibernate.many_to_many.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
public class RunIt {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/product_homework/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();


            List<User> users = session.createQuery("from User").getResultList();
            List<Product> products = session.createQuery("from Product").getResultList();
            users.get(1).getProducts().add(products.get(3));
            users.get(1).getProducts().add(products.get(1));








    } finally {
        factory.close();
        if (session != null) {
            session.close();
        }
    }
    }
}
