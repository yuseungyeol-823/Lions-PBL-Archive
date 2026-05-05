package IoC_DI.package2;

import IoC_DI.role.Lion;
import IoC_DI.role.Member;
import java.util.List;
import java.util.Optional;

public class MockMemberRepository implements MemberRepository {
    @Override
    public void save(Member member) {
        // Mock 저장소는 데이터를 저장하지 않습니다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.of(new Lion("유승열", "정보통신공학과", 14, "백엔드", "20230001"));
    }

    @Override
    public List<Member> findAll() {
        return List.of(new Lion("유승", "정통", 14, "백엔드", "20230002"));
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}