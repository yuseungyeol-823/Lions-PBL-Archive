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
}