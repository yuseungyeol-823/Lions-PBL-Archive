package lionSpringBoot.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String position; // Staff 고유 필드
}