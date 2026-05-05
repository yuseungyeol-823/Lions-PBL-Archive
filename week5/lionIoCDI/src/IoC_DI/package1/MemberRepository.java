package IoC_DI.package1;

import IoC_DI.role.Member;
import java.util.*;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();

    // 멤버 저장
    public void save(Member member) {
        members.add(member);
    }

    // 이름으로 멤버 검색
    public Optional<Member> findByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst();
    }

    // 전체 멤버 조회
    public List<Member> findAll() {
        return new ArrayList<>(members);
    }

    // 이름 중복 확인
    public boolean existsByName(String name) {
        return members.stream().anyMatch(m -> m.getName().equals(name));
    }
}