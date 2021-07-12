package com.flamexander.hibernate.product_homework;

import com.flamexander.hibernate.PrepareDataApp;

import com.flamexander.hibernate.crud.SimpleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProductDao {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/product_homework/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try{


            init();
            createProduct();
            updateProduct();
            readProduct();



        } catch (Exception e) {
        e.printStackTrace();
    } finally {
        shutdown();
    }
    }


    //CRUD:

    //Создание экземпляра
    public static void createProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product Milk = new Product("Milk", 72);
            System.out.println(Milk);
            session.save(Milk);
            System.out.println(Milk);
            session.getTransaction().commit();
            System.out.println(Milk);
        }
    }



    //Чтение экземпляра
    public static void readProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, 1L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }



    //Обновление экземпляра
    public static void updateProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            product.setPrice(87);
            session.getTransaction().commit();
        }
    }


     //Удаление продукта
    public static void deleteProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, 1L);
            session.delete(product);
            session.getTransaction().commit();
        }
    }



    public static void shutdown() {
        factory.close();
    }
}
