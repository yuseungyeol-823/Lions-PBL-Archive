package lionSpringBoot.demo.dto;

import lionSpringBoot.demo.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private Long id; // 8주차에 추가된 DB 기본키(PK)
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName; // "아기사자" 또는 "운영진" 출력용
    private String studentId; // Staff일 때는 null로 나감
    private String position;  // Lion일 때는 null로 나감

    // 팩토리 메서드: Member 엔티티 하나를 주면 알맞게 파싱해서 응답 상자로 변환
    public static MemberResponse from(Member member) {
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        response.setMajor(member.getMajor());
        response.setGeneration(member.getGeneration());
        response.setPart(member.getPart());

        // Enum의 코드가 LION인지 STAFF인지 판별하여 displayName("아기사자"/"운영진")을 세팅합니다.
        if (member.getRoleType() != null) {
            response.setRoleName(member.getRoleType().getDisplayName());
        }

        response.setStudentId(member.getStudentId());
        response.setPosition(member.getPosition());

        return response;
    }
}