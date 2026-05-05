package IoC_DI.package1;

import IoC_DI.role.Member;
import java.util.List;

public class MemberService {
    // Step 1 제약사항: 내부에서 직접 생성하여 강하게 결합함
    private final MemberRepository repository = new MemberRepository();

    // 중복 확인 후 멤버 등록
    public void join(Member member) {
        if (repository.existsByName(member.getName())) {
            System.out.println("  등록 실패: 이미 존재하는 이름입니다.");
            return;
        }
        repository.save(member);
        System.out.println("  등록 완료: " + member.getName());
    }

    // 전체 멤버 조회
    public List<Member> findMembers() {
        return repository.findAll();
    }

    // 이름으로 멤버 검색
    public Member findOne(String name) {
        return repository.findByName(name).orElse(null);
    }
}