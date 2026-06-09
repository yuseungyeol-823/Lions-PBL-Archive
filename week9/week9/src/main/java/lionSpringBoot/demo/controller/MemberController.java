package lionSpringBoot.demo.controller;

import lionSpringBoot.demo.domain.Member;
import lionSpringBoot.demo.dto.*;
import lionSpringBoot.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 1. Lion(아기사자) 등록 API (POST /members/lions)
     */
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        Member lion = memberService.createLion(request);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict (이름 중복)
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(lion)); // 201 Created
    }

    /**
     * 2. Staff(운영진) 등록 API (POST /members/staffs)
     */
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Member staff = memberService.createStaff(request);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(staff)); // 201 Created
    }

    /**
     * 3. ID 기반 단일 멤버 조회 API (GET /members/{id})
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }

        return ResponseEntity.ok(MemberResponse.from(member)); // 200 OK
    }

    /**
     * 4. 전체 멤버 조회 API (GET /members)
     */
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<Member> members = memberService.findMembers();

        // 엔티티 리스트를 하나씩 응답 DTO 상자로 변환하여 리스트로 묶어 반환합니다.
        List<MemberResponse> response = members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response); // 200 OK
    }

    /**
     * 5. ID 기반 Lion(아기사자) 정보 수정 API (PUT /members/lions/{id})
     */
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        Member updatedLion = memberService.updateLion(id, request);

        if (updatedLion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }

        return ResponseEntity.ok(MemberResponse.from(updatedLion)); // 200 OK
    }

    /**
     * 6. ID 기반 Staff(운영진) 정보 수정 API (PUT /members/staffs/{id})
     */
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        Member updatedStaff = memberService.updateStaff(id, request);

        if (updatedStaff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }

        return ResponseEntity.ok(MemberResponse.from(updatedStaff)); // 200 OK
    }

    /**
     * 7. ID 기반 멤버 삭제 API (DELETE /members/{id})
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean isDeleted = memberService.deleteMember(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
}