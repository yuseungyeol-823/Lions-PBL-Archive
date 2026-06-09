package lionSpringBoot.demo.repository;

import lionSpringBoot.demo.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    // member_id 값을 필터 조건으로 자식 데이터 목록을 추출하는 쿼리 메서드
    List<Assignment> findByMemberId(Long memberId);
}