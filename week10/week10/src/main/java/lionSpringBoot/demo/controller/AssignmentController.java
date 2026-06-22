package lionSpringBoot.demo.controller;

import lionSpringBoot.demo.domain.Assignment;
import lionSpringBoot.demo.dto.AssignmentCreateRequest;
import lionSpringBoot.demo.dto.AssignmentResponse;
import lionSpringBoot.demo.dto.AssignmentUpdateRequest;
import lionSpringBoot.demo.assignment.service.AssignmentService;
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

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request) {

        Assignment assignment = assignmentService.createAssignment(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(assignment));
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(@PathVariable Long memberId) {
        List<Assignment> assignments = assignmentService.findAssignmentsByMemberId(memberId);
        List<AssignmentResponse> response = assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findById(id);
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    /**
     * 🆕 10주차 추가: 전체 과제 목록 조회 API (GET /assignments)
     */
    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.findAllAssignments();
        List<AssignmentResponse> response = assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * 🆕 10주차 추가: 과제 제목 키워드 검색 API (GET /assignments/search?keyword=키워드)
     */
    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchAssignmentsByTitle(@RequestParam String keyword) {
        List<Assignment> assignments = assignmentService.searchAssignmentsByTitle(keyword);
        List<AssignmentResponse> response = assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request) {

        Assignment updated = assignmentService.updateAssignment(id, request);
        return ResponseEntity.ok(AssignmentResponse.from(updated));
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}