package lionSpringBoot.demo.assignment.service;

import lionSpringBoot.demo.domain.Assignment;
import lionSpringBoot.demo.dto.AssignmentCreateRequest;
import lionSpringBoot.demo.dto.AssignmentUpdateRequest;
import lionSpringBoot.demo.repository.AssignmentRepository;
import lionSpringBoot.demo.domain.Member;
import lionSpringBoot.demo.global.AssignmentNotFoundException;
import lionSpringBoot.demo.global.MemberNotFoundException;
import lionSpringBoot.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Assignment createAssignment(Long memberId, AssignmentCreateRequest request) {
        // 🆕 멤버 조회 실패 시 곧바로 NotFoundException 발생
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAssignmentsByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
    }

    // 🆕 10주차 추가: 모든 과제 리스트 조회 로직
    public List<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    // 🆕 10주차 추가: 과제 제목 키워드 검색 로직
    public List<Assignment> searchAssignmentsByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));

        assignment.updateInfo(request.getTitle(), request.getDescription());
        return assignment;
    }

    @Transactional
    public boolean deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new AssignmentNotFoundException(id);
        }
        assignmentRepository.deleteById(id);
        return true;
    }
}