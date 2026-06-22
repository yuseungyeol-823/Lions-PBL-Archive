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

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        // 🆕 전역 예외 처리기가 에러를 핸들링하므로 null 체크 로직 완전 제거
        Member lion = memberService.createLion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(lion));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Member staff = memberService.createStaff(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(staff));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    /**
     * 🆕 10주차 파트별 검색 필터링 조건 추가 (required = false)
     * 예시: GET /members?part=백엔드 혹은 GET /members 두 조건 모두 커버 가능
     */
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(@RequestParam(required = false) String part) {
        List<Member> members;
        if (part != null && !part.isBlank()) {
            members = memberService.findMembersByPart(part);
        } else {
            members = memberService.findMembers();
        }

        List<MemberResponse> response = members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        Member updatedLion = memberService.updateLion(id, request);
        return ResponseEntity.ok(MemberResponse.from(updatedLion));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        Member updatedStaff = memberService.updateStaff(id, request);
        return ResponseEntity.ok(MemberResponse.from(updatedStaff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}