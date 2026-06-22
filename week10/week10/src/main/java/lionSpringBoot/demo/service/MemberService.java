package lionSpringBoot.demo.service;

import lionSpringBoot.demo.domain.Member;
import lionSpringBoot.demo.domain.RoleType;
import lionSpringBoot.demo.dto.*;
import lionSpringBoot.demo.global.DuplicateMemberException;
import lionSpringBoot.demo.global.MemberNotFoundException;
import lionSpringBoot.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    @Transactional
    public Member createLion(LionCreateRequest request) {
        // 🆕 null 반환 대신 중복 예외 throw
        if (repository.findByName(request.getName()).isPresent()) {
            throw new DuplicateMemberException(request.getName());
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

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        // 🆕 null 반환 대신 중복 예외 throw
        if (repository.findByName(request.getName()).isPresent()) {
            throw new DuplicateMemberException(request.getName());
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

    // 🆕 findById 실패 시 예외 throw 전략 적용
    public Member findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    // 🆕 10주차 추가: 파트별 멤버 필터링 조회 로직
    public List<Member> findMembersByPart(String part) {
        return repository.findByPart(part);
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());

        return member;
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());

        return member;
    }

    @Transactional
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            throw new MemberNotFoundException(id);
        }
        repository.deleteById(id);
        return true;
    }
}