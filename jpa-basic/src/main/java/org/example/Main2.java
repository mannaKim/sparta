package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.entity.Tutor;

public class Main2 {

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
            // 비영속
            Tutor tutor = new Tutor(1L, "wonuk", 100);

            // 영속
            System.out.println("persist 전");
            em.persist(tutor);
            System.out.println("persist 후");

            Tutor findTutor = em.find(Tutor.class, 1L);

            System.out.println("findTutor.getId() = " + findTutor.getId());
            System.out.println("findTutor.getName() = " + findTutor.getName());
            System.out.println("findTutor.getAge() = " + findTutor.getAge());

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
