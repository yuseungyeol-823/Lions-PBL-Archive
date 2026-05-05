package IoC_DI.package2;

import IoC_DI.role.Member;
import java.util.List;

public class MemberService {
    // 특정 클래스가 아닌 인터페이스에 의존합니다.
    private final MemberRepository repository;

    // 생성자를 통해 외부에서 의존성을 주입받습니다 (DI).
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void join(Member member) {
        if (repository.existsByName(member.getName())) {
            System.out.println("등록 실패: 이미 존재하는 이름입니다.");
            return;
        }
        repository.save(member);
        System.out.println("등록 완료: " + member.getName());
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Member findOne(String name) {
        return repository.findByName(name).orElse(null);
    }
}