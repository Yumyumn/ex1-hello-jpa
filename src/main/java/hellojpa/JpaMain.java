package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        // 로딩 시점에 딱 하나만 만들어야 한다. (DB당 1개)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 내부적으로 database connection을 물고 동작한다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 실제 동작 코드 작성
        try{
            /*// 삽입
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
//            member.setId(2L);
//            member.setName("HelloB");
            em.persist(member);
            */
            /*// 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */
            /*
            // 삭제
            em.remove(findMember);
             */
            /*// 수정
            Member findMember = em.find(Member.class, 1L);
            // transaction을 커밋하기 직전에 JPA가 변화된 내용을 감지하고 update문을 날리고 tx을 커밋한다
            findMember.setName("HelloJPA");
             */

            // JPQL
            // JPA는 절대 테이블을 대상으로 쿼리를 만들지 않는다. 멤버 객체를 대상으로 한 쿼리문이다.
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            // 만약 페이징을 하고 싶다면
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0) // 1번부터（０이첫번째） 10개 가져와! 라는 뜻
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            // transaction을 commit하는 시점에 영속성 컨텍스트에 있는 애가 DB에 쿼리가 날라가게 된다.
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            // entity manager가 내부적으로 database connection을 물고 동작하기 때문에 이것을 닫아주는게 중요하다
            em.close();
        }
        // was가 내려갈 때 꼭 닫아줘야 한다.
        emf.close();
    }
}
