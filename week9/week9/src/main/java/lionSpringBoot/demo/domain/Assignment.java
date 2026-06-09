package lionSpringBoot.demo.domain;

import jakarta.persistence.*;
import lionSpringBoot.demo.domain.Member; // 🆕 기존 Member 패키지 참조
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // DB assignment 테이블에 외래 키 컬럼 개설
    private Member member;

    public Assignment(String title, String description, Member member) {
        this.title = title;
        this.description = description;
        this.member = member;
    }

    public void updateInfo(String title, String description) {
        this.title = title;
        this.description = description;
    }
}