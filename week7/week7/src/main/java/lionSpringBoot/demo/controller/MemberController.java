package lionSpringBoot.demo.controller;

import lionSpringBoot.demo.domain.role.Lion;
import lionSpringBoot.demo.domain.role.Staff;
import lionSpringBoot.demo.dto.*;
import lionSpringBoot.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor // 6주차에 배운 자동 의존성 주입 마법!
public class MemberController {

    private final MemberService memberService;

    // 🆕 1. Lion(아기사자) 등록 API
    @PostMapping("/lions")
    public ResponseEntity<?> createLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);

        // 이름이 중복되어 null이 반환된 경우 -> 409 Conflict
        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // 생성 성공 -> 201 Created와 함께 상자에 담아 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(lion));
    }

    // 🆕 2. Staff(운영진) 등록 API
    @PostMapping("/staffs")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);

        // 이름이 중복된 경우 -> 409 Conflict
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // 생성 성공 -> 201 Created와 함께 상자에 담아 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(staff));
    }
    // 🆕 3. 단일 멤버 조회 API
    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        // 먼저 이름으로 멤버를 조회합니다 (Optional 반환인 경우에 맞춘 처리)
        var memberOpt = memberService.findByName(name); // 기존 서비스의 조회 메서드 활용

        // 만약 Optional이 비어있거나 멤버가 없으면 -> 404 Not Found
        if (memberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        lionSpringBoot.demo.domain.role.Member member = memberOpt.get();

        // 자식 클래스 타입(Lion인지 Staff인지)에 따라 알맞은 응답 상자에 담아 반환
        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else if (member instanceof Staff) {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 🆕 4. Lion(아기사자) 정보 수정 API
    @PutMapping("/lions/{name}")
    public ResponseEntity<?> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        Lion updatedLion = memberService.updateLion(name, request);

        // 수정할 멤버가 없는 경우 -> 404 Not Found
        if (updatedLion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 수정 성공 -> 200 OK와 함께 수정된 정보 반환
        return ResponseEntity.ok(LionResponse.from(updatedLion));
    }

    // 🆕 5. Staff(운영진) 정보 수정 API
    @PutMapping("/staffs/{name}")
    public ResponseEntity<?> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        Staff updatedStaff = memberService.updateStaff(name, request);

        // 수정할 멤버가 없는 경우 -> 404 Not Found
        if (updatedStaff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 수정 성공 -> 200 OK와 함께 수정된 정보 반환
        return ResponseEntity.ok(StaffResponse.from(updatedStaff));
    }

    // 🆕 6. 멤버 삭제 API
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        boolean isDeleted = memberService.deleteMember(name);

        // 삭제 실패 (삭제할 멤버가 없는 경우 등) -> 404 Not Found
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 삭제 성공 -> 204 No Content (반환할 알맹이 body 데이터가 없음을 의미)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}