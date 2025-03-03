package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.entity.Tutor;

public class Main3 {
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

            // DB 조회
            Tutor findTutor1 = em.find(Tutor.class, 1L);
            // 1차 캐시 조회
            Tutor findTutor2 = em.find(Tutor.class, 1L);

            System.out.println("findTutor1 == findTutor2 : " + findTutor1.equals(findTutor2));

            // transaction이 commit되며 실제 SQL이 실행된다.
            transaction.commit();
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
