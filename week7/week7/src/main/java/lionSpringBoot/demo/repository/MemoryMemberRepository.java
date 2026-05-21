package lionSpringBoot.demo.repository;

import org.springframework.stereotype.Repository;
import lionSpringBoot.demo.domain.role.Member;
import java.util.*;

@Repository //
public class MemoryMemberRepository implements MemberRepository {
    private List<Member> members = new ArrayList<>();

    @Override
    public void save(Member member) {
        members.add(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return members.stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members);
    }

    @Override
    public boolean existsByName(String name) {
        return members.stream().anyMatch(m -> m.getName().equals(name));
    }

    @Override
    public void updateByName(String name, Member newMember) {
        // 리스트를 순회하며 이름이 일치하는 멤버를 찾아 교체합니다.
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                members.set(i, newMember);
                return;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        // removeIf는 조건에 맞는 데이터가 삭제되면 true를 반환합니다.
        return members.removeIf(member -> member.getName().equals(name));
    }
}