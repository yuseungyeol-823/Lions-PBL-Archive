package IoC_DI.package2;

import IoC_DI.role.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {
    private List<Member> members = new ArrayList<>();

    @Override
    public void save(Member member) {
        members.add(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst();
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