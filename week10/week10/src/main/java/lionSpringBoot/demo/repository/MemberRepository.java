package lionSpringBoot.demo.repository;

import lionSpringBoot.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    // 🆕 10주차 파트별 필터링 기능을 위한 쿼리 메서드 추가
    List<Member> findByPart(String part);
}