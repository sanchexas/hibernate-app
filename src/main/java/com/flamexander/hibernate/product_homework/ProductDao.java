package com.flamexander.hibernate.product_homework;

import com.flamexander.hibernate.PrepareDataApp;

import com.flamexander.hibernate.crud.SimpleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {
    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }
//        public static void init() {
//        PrepareDataApp.forcePrepareData();
//        factory = new Configuration()
//                .configure("configs/product_homework/hibernate.cfg.xml")
//                .buildSessionFactory();
//    }

//    public static void main(String[] args) {
//        try{
//
//
//            init();
//            createProduct();
//            updateProduct();
//            readProduct();
//
//
//
//        } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        shutdown();
//    }
//    }


    //CRUD:

    //Создание экземпляра
    public  void createProduct(){
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
    public void readProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, 1L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }



    //Обновление экземпляра
    public void updateProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            product.setPrice(87);
            session.getTransaction().commit();
        }
    }


     //Удаление продукта
    public void deleteProduct(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, 1L);
            session.delete(product);
            session.getTransaction().commit();
        }
    }
    public Product findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }
    public List<Product> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = (List<Product>)session.createQuery("from Product", List.class);
            session.getTransaction().commit();
            return products;
        }
    }


    public void shutdown() {
        factory.close();
    }
}
