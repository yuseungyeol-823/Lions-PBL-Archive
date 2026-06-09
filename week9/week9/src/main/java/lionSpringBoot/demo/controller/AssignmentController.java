package lionSpringBoot.demo.controller;

import lionSpringBoot.demo.domain.Assignment;
import lionSpringBoot.demo.dto.AssignmentCreateRequest;
import lionSpringBoot.demo.dto.AssignmentResponse;
import lionSpringBoot.demo.dto.AssignmentUpdateRequest;
import lionSpringBoot.demo.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    // 1. 특정 멤버에게 과제 생성 배정 (POST /members/{memberId}/assignments)
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request) {

        Assignment assignment = assignmentService.createAssignment(memberId, request);
        if (assignment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(assignment));
    }

    // 2. 멤버별 제출 과제 목록 조회 (GET /members/{memberId}/assignments)
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(@PathVariable Long memberId) {
        List<Assignment> assignments = assignmentService.findAssignmentsByMemberId(memberId);
        List<AssignmentResponse> response = assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 3. 과제 식별 번호 단건 조회 (GET /assignments/{id})
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findById(id);
        if (assignment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    // 4. 과제 본문 내용 수정 (PUT /assignments/{id})
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request) {

        Assignment updated = assignmentService.updateAssignment(id, request);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(AssignmentResponse.from(updated));
    }

    // 5. 과제 리스트에서 영구 삭제 (DELETE /assignments/{id})
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        boolean isDeleted = assignmentService.deleteAssignment(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}