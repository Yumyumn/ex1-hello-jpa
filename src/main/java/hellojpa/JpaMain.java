package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        // 로딩 시점에 딱 하나만 만들어야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 내부적으로 database connection을 물고 동작한다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 실제 동작 코드 작성
        try{
            /* // 삽입
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
            */
            /*// 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            // 삭제
            em.remove(findMember);
             */
            /*// 수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
             */
            // JPQL 사용
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            // JPA는 절대 테이블을 대상으로 쿼리를 만들지 않는다. 멤버 객체를 대상으로 한 쿼리문이다.
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            // 만약 페이징을 하고 싶다면
            List<Member> resultP = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 1번부터 10개 가져와! 라는 뜻
                    .setMaxResults(10)
                    .getResultList();
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
