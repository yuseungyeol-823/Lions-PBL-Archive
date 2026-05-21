package lionSpringBoot.demo.repository;

import lionSpringBoot.demo.domain.role.Member; // ⚠️ 만약 부모 클래스가 Role이면 Role로 임포트하세요!
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findByName(String name);

    List<Member> findAll();

    boolean existsByName(String name);

    // 🆕 [7주차 추가] 이름으로 기존 멤버를 찾아 새로운 정보로 교체
    void updateByName(String name, Member member); // 부모가 Role이면 Role member

    // 🆕 [7주차 추가] 이름으로 멤버를 삭제하고 성공 여부 반환
    boolean deleteByName(String name);
}