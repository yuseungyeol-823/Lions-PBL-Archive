package lionSpringBoot.demo.service;

import lionSpringBoot.demo.domain.role.Lion;
import lionSpringBoot.demo.domain.role.Member;
import lionSpringBoot.demo.domain.role.Staff;
import lionSpringBoot.demo.dto.*;
import lionSpringBoot.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 6주차 의존성 자동 주입
public class MemberService {

    private final MemberRepository repository;

    /**
     * 기존 5~6주차 비즈니스 로직 유지
     */
    public void join(Member member) {
        if (repository.existsByName(member.getName())) return;
        repository.save(member);
    }

    // 컨트롤러의 단일 조회 기능에서 사용할 기존 조회 메서드
    public Optional<Member> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }


    /**
     * 🆕 7주차 REST API를 위한 CRUD 비즈니스 로직
     */

    // [1] Lion 생성 로직
    public Lion createLion(LionCreateRequest request) {
        // 이름 중복 검사 -> 이미 존재하면 null 반환 (컨트롤러에서 409 Conflict 처리)
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Lion lion = new Lion();
        lion.setName(request.getName());
        lion.setMajor(request.getMajor());
        lion.setGeneration(request.getGeneration());
        lion.setPart(request.getPart());
        lion.setStudentId(request.getStudentId()); // Lion 고유 필드

        repository.save(lion);
        return lion;
    }

    // [2] Staff 생성 로직
    public Staff createStaff(StaffCreateRequest request) {
        // 이름 중복 검사 -> 이미 존재하면 null 반환
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Staff staff = new Staff();
        staff.setName(request.getName());
        staff.setMajor(request.getMajor());
        staff.setGeneration(request.getGeneration());
        staff.setPart(request.getPart());
        staff.setPosition(request.getPosition()); // Staff 고유 필드

        repository.save(staff);
        return staff;
    }

    // [3] Lion 수정 로직
    public Lion updateLion(String name, LionUpdateRequest request) {
        // 수정할 타겟이 없으면 null 반환 (컨트롤러에서 404 Not Found 처리)
        if (!repository.existsByName(name)) {
            return null;
        }

        Lion updatedLion = new Lion();
        updatedLion.setName(name); // 이름은 기존 식별자 유지
        updatedLion.setMajor(request.getMajor());
        updatedLion.setGeneration(request.getGeneration());
        updatedLion.setPart(request.getPart());
        updatedLion.setStudentId(request.getStudentId());

        repository.updateByName(name, updatedLion);
        return updatedLion;
    }

    // [4] Staff 수정 로직
    public Staff updateStaff(String name, StaffUpdateRequest request) {
        // 수정할 타겟이 없으면 null 반환
        if (!repository.existsByName(name)) {
            return null;
        }

        Staff updatedStaff = new Staff();
        updatedStaff.setName(name);
        updatedStaff.setMajor(request.getMajor());
        updatedStaff.setGeneration(request.getGeneration());
        updatedStaff.setPart(request.getPart());
        updatedStaff.setPosition(request.getPosition());

        repository.updateByName(name, updatedStaff);
        return updatedStaff;
    }

    // [5] 멤버 삭제 로직
    public boolean deleteMember(String name) {
        return repository.deleteByName(name);
    }
}