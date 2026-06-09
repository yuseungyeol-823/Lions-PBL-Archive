package lionSpringBoot.demo.service;

import lionSpringBoot.demo.domain.Assignment;
import lionSpringBoot.demo.dto.AssignmentCreateRequest;
import lionSpringBoot.demo.dto.AssignmentUpdateRequest;
import lionSpringBoot.demo.repository.AssignmentRepository;
import lionSpringBoot.demo.domain.Member;
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
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return null; // 컨트롤러단에서 404 예외 핸들링 연동
        }

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAssignmentsByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) {
            return null;
        }
        assignment.updateInfo(request.getTitle(), request.getDescription());
        return assignment;
    }

    @Transactional
    public boolean deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            return false;
        }
        assignmentRepository.deleteById(id);
        return true;
    }
}