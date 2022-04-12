package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // jpa가 사용하는 애구나, 요거는 내가 관리해야겠다 라고 인식
public class Member {
    @Id // pk가 뭔지 알려주기
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    // JPA는 내부적으로 reflection을 사용하기 때문에 동적으로 객체를 생성해야 한다 -> 기본 생성자가 있어야
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
