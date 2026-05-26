package lionSpringBoot.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 👈 이 클래스가 DB 테이블과 1:1로 매핑되는 엔티티임을 지정합니다.
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) // JPA 필수: protected 기본 생성자 자동 생성
public class Member {

    @Id // 👈 PK (기본키) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 👈 MySQL의 auto_increment (자동 번호 생성) 사용
    private Long id;

    private String name;
    private String major;
    private int generation;
    private String part;

    @Enumerated(EnumType.STRING) // 👈 Enum 이름을 DB에 문자열(LION, STAFF) 그대로 저장합니다.
    private RoleType roleType;

    // 아기사자(LION)일 때만 값이 존재하고, 운영진은 null이 들어갑니다.
    private String studentId;

    // 운영진(STAFF)일 때만 값이 존재하고, 아기사자는 null이 들어갑니다.
    private String position;

    // 전체 필드 생성자 (객체 생성 시 사용)
    public Member(String name, String major, int generation, String part, RoleType roleType, String studentId, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public void updateInfo(String major, int generation, String part) {
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updatePosition(String position) {
        this.position = position;
    }
}