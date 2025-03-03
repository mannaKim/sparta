package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Book;
import org.example.entity.Product;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity");

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {

            Book book = new Book("wonuk", "spring-advanced", BigDecimal.TEN);
            em.persist(book);

            // JOINED
//            em.flush();
//            em.clear();
//
//            Book findBook = em.find(Book.class, book.getId());

            // TABLE_PER_CLASS
            em.flush();
            em.clear();

            Product findProduct = em.find(Product.class, book.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}