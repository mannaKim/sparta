package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.entity.Tutor;

public class Main4 {
    public static void main(String[] args) {
        // EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();
        // Transaction 생성
        EntityTransaction transaction = em.getTransaction();

        // 트랜잭션 시작
        transaction.begin();

        try {
            System.out.println("트랜잭션 시작");
            Tutor tutor1 = new Tutor(1L, "wonuk1", 100);
            Tutor tutor2 = new Tutor(2L, "wonuk2", 200);

            em.persist(tutor1);
            em.persist(tutor2);

            System.out.println("트랜잭션 Commit 전");
            // transaction이 commit되며 실제 SQL이 실행된다.
            transaction.commit();
            System.out.println("트랜잭션 Commit 후");
        } catch (Exception e) {
            // 실패 -> 롤백
            e.printStackTrace();
            transaction.rollback();
        } finally {
            // 엔티티 매니저 연결 종료
            em.close();
        }

        emf.close();
    }
}
