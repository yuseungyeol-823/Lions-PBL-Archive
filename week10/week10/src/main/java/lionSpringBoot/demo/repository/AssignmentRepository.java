package lionSpringBoot.demo.repository;

import lionSpringBoot.demo.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByMemberId(Long memberId);

    // 🆕 10주차 추가: 과제 제목에 특정 검색 키워드가 포함되어 있는지 조회 (LIKE %keyword% 효과)
    List<Assignment> findByTitleContaining(String keyword);
}