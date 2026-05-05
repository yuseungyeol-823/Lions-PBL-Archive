package IoC_DI.package2;

import IoC_DI.role.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    boolean existsByName(String name);
}