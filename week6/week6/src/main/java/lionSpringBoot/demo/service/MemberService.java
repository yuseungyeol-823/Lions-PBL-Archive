package lionSpringBoot.demo.service;

import lionSpringBoot.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor; // 👈 롬복 Import 추가
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 👈 생성자를 자동으로 만들어주는 마법의 어노테이션입니다.
public class MemberService {

    // ⚠️ 중요: 반드시 final 키워드가 붙어있어야 롬복이 인식합니다.
    private final MemberRepository repository;

    /* * [삭제됨] @Autowired 생성자
     * @RequiredArgsConstructor가 아래 코드를 배후에서 자동으로 생성해줍니다.
     * * public MemberService(MemberRepository repository) {
     * this.repository = repository;
     * }
     */

    // ... 기존 join, findMembers 등 비즈니스 로직 유지
}