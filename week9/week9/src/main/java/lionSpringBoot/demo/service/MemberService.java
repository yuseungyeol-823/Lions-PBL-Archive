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
@Transactional(readOnly = true) // 🆕 9주차 트랜잭션 최적화 전략 추가
public class MemberService {

    private final MemberRepository repository;

    @Transactional // 🆕 데이터 쓰기 작업 활성화
    public Member createLion(LionCreateRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            return null;
        }

        Member lion = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.LION,
                request.getStudentId(),
                null
        );

        return repository.save(lion);
    }

    @Transactional // 🆕 데이터 쓰기 작업 활성화
    public Member createStaff(StaffCreateRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            return null;
        }

        Member staff = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );

        return repository.save(staff);
    }

    public Member findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    @Transactional // 🆕 데이터 쓰기 작업 활성화
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());

        return member;
    }

    @Transactional // 🆕 데이터 쓰기 작업 활성화
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());

        return member;
    }

    @Transactional // 🆕 데이터 쓰기 작업 활성화
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}