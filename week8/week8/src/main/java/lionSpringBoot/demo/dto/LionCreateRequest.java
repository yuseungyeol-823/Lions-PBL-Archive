package lionSpringBoot.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LionCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId; // Lion 고유 필드
}