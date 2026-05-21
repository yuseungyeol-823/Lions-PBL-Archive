package lionSpringBoot.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LionUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String studentId; // name 제외
}