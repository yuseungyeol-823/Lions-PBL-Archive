package lionSpringBoot.demo.service;

import lionSpringBoot.demo.domain.Member;
import lionSpringBoot.demo.domain.RoleType;
import lionSpringBoot.demo.dto.*;
import lionSpringBoot.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 데이터의 안전한 조회를 위한 기본 설정
public class MemberService {

    private final MemberRepository repository;

    /**
     * 1. Lion(아기사자) 생성 로직
     */
    @Transactional // 데이터 저장/수정이 일어나는 메서드에는 반드시 이 어노테이션이 필요합니다.
    public Member createLion(LionCreateRequest request) {
        // 이름 중복 검사: 7주차 패턴 유지 (이미 존재하면 null 반환)
        if (repository.findByName(request.getName()).isPresent()) {
            return null;
        }

        // 7주차 상속 구조 대신, 하나의 Member 엔티티로 생성하되 RoleType을 LION으로 지정합니다.
        Member lion = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.LION,       // roleType
                request.getStudentId(), // studentId (Lion 고유 필드)
                null                 // position은 null로 세팅
        );

        // jpaRepository.save() 호출 시 DB에 영구 저장되며 자동으로 부여된 id가 객체에 채워집니다.
        return repository.save(lion);
    }

    /**
     * 2. Staff(운영진) 생성 로직
     */
    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            return null;
        }

        Member staff = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.STAFF,      // roleType
                null,                // studentId는 null로 세팅
                request.getPosition()   // position (Staff 고유 필드)
        );

        return repository.save(staff);
    }

    /**
     * 3. ID 기반 단건 조회 로직
     */
    public Member findById(Long id) {
        // findById()는 결과가 없을 수 있으므로 Optional을 반환합니다. 없으면 null을 리턴합니다.
        return repository.findById(id).orElse(null);
    }

    /**
     * 4. 전체 멤버 조회 로직
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    /**
     * 5. Lion 정보 수정 로직 (이름 대신 ID 기준)
     */
    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        // 엔티티 내부 비즈니스 메서드를 호출하여 필드 값을 변경합니다 (더티 체킹 발동)
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());

        return member; // 영속성 컨텍스트가 관리하므로 save()를 명시적으로 안 해도 메서드가 끝날 때 자동으로 DB에 반영됩니다.
    }

    /**
     * 6. Staff 정보 수정 로직 (이름 대신 ID 기준)
     */
    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());

        return member;
    }

    /**
     *  7. 멤버 삭제 로직 (이름 대신 ID 기준)
     */
    @Transactional
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}