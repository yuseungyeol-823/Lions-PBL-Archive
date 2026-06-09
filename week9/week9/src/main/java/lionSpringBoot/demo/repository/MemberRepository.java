package lionSpringBoot.demo.repository;

import lionSpringBoot.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 🆕 JpaRepository<엔티티타입, PK타입>을 상속받는 것만으로 대다수의 CRUD 메서드가 자동 생성됩니다!
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 🆕 쿼리 메서드 규칙: findBy + 필드명(Name)을 적어주면
    // 스프링 데이터 JPA가 "SELECT * FROM member WHERE name = ?" 쿼리를 자동으로 조립해 실행합니다.
    Optional<Member> findByName(String name);
}